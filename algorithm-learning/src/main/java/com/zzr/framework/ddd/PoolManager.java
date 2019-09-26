package com.zzr.framework.ddd;

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
    private ExecutorService  //初始化主规则线程池,。主规则线程池之所以不留过多的排队空间，是为了让服务在请求过多，压力过大的时候，自动降级到兜底策略
            mainPool = new ThreadPoolExecutor(
            100,
            100,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10));

    /**
     * 兜底（降级）线程池
     */
    private ExecutorService fallbackPool;

    /**
     * 影子实验线程池
     */
    private ExecutorService shadowExperimentPool;

    /**
     * 推荐流程线程池，用来并行执行召回、过滤等步骤
     */
    private ExecutorService recommendationFlowPool;

    /**
     * 发送JMQ消息
     */
    private ExecutorService sendMessagePool;

    private PoolManager() {}

    /**
     * 获取线程池管理器实例
     * @return 线程池管理器实例
     */
    public static PoolManager getInstance() {
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
     * 获取主规则集线程池
     * @return 主规则集线程池
     */
    public ExecutorService getMainPool() {
        return mainPool;
    }


}
