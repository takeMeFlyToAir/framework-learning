package com.zzr.framework.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: qiuyujiang
 * @Date: 2019/2/14.
 * @Description: 线程池管理器
 */
public class PoolManager {


    /**
     * 单例
     */
    private static PoolManager instance;

    /**
     * 主规则线程池
     */
    private ExecutorService mainPool;

    /**
     * 兜底（降级）线程池
     */
    private ExecutorService fallbackPool;

    /**
     * 影子实验线程池
     */
    private ExecutorService shadowExperimentPool;

    /**
     * 发送JMQ消息
     */
    private ExecutorService sendMessagePool;

    /**
     * 推荐流程线程池，用来并行执行召回、过滤等步骤
     */
    private ExecutorService recommendationFlowPool;

    public PoolManager() {

        init();
    }

    /**
     * 获取线程池管理器实例
     * @return 线程池管理器实例
     */
    public  PoolManager getInstance() {
        if (instance == null) {
            synchronized (PoolManager.class) {
                if (instance == null) {
                    instance = new PoolManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化所有线程池
     */
    public void init() {
        //初始化主规则线程池,。主规则线程池之所以不留过多的排队空间，是为了让服务在请求过多，压力过大的时候，自动降级到兜底策略
        mainPool = new ThreadPoolExecutor(
                100,
                100,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));

        //初始化兜底（降级）线程池。兜底线程池之所以留出较多的排队空间，是为了尽可能让进来的请求都有正常结果返回
        fallbackPool = new ThreadPoolExecutor(
                100,
                300,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        //初始化推荐流程线程池，用来并行执行召回、过滤等步骤
        //修改最大线程数为500，是为了应对某些召回或者过滤超时问题，在用完以后及时销毁
        recommendationFlowPool = new ThreadPoolExecutor(
                200,
                500,
                10L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(50));

        //初始化影子实验线程池，用来执行影子状态的实验，影子实验不会很多，所以核心线程数设置较少
        shadowExperimentPool = new ThreadPoolExecutor(
                50,
                100,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));


        //初始化发送消息队列的线程池
        sendMessagePool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
    }

    /**
     * 销毁所有线程池
     */
    public void destroy() {
        mainPool.shutdown();
        fallbackPool.shutdown();
        recommendationFlowPool.shutdown();
        shadowExperimentPool.shutdown();
        sendMessagePool.shutdown();

    }

    /**
     * 获取主规则集线程池
     * @return 主规则集线程池
     */
    public ExecutorService getMainPool() {
        return mainPool;
    }

    /**
     * 获取兜底规则集线程池
     * @return
     */
    public ExecutorService getFallbackPool() {
        return fallbackPool;
    }

    /**
     * 获取推荐流程线程池
     * @return
     */
    public ExecutorService getRecommendationFlowPool() {
        return recommendationFlowPool;
    }

    /**
     * 获取推影子实验线程池
     * @return
     */
    public ExecutorService getShadowExperimentPool() {
        return shadowExperimentPool;
    }


    /**
     * 获取发送JMQ消息线程池
     * @return
     */
    public ExecutorService getSendMessagePool() {
        return sendMessagePool;
    }
}
