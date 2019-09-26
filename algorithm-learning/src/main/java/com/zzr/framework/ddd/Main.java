package com.zzr.framework.ddd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhaozhirong on 2019/8/29.
 */
public class Main {
    public static void main(String[] args) {
        List<RecommendationFlow> mainFlows = new ArrayList<>();
        List<RecommendationFlow> fallbackFlows = new ArrayList<>();
        List<RecRule> mainRecRules = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        // 并行读取主规则集
        // 主规则集和兜底规则集需要在独立的两个try-catch里，避免主规则集报错，无法走兜底的情况
        try {
            // 若该推荐位达到了限流阈值，则不走主规则集，直接降级走兜底
            List<Long> timeList = new ArrayList<>();
            timeList.add(100L);
            timeList.add(160L);
            timeList.add(100L);
                for (Long time : timeList) {
                    RecommendationFlow flow = new RecommendationFlow(time);
                    Future<RecRule> future = PoolManager.getInstance().getMainPool().submit(flow);
                    flow.setFuture(future);
                    mainFlows.add(flow);
            }
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
        startTime = System.currentTimeMillis();
        // 获取主规则集、兜底规则集的结果
        long costTimeForGet = 0;
        long startTimeForGet;
        long timeoutMilliseconds;
        for (RecommendationFlow flow : mainFlows){
            startTimeForGet = System.currentTimeMillis();
            timeoutMilliseconds = flow.getTime();
            timeoutMilliseconds = costTimeForGet > timeoutMilliseconds ? 1 : timeoutMilliseconds - costTimeForGet;
            // 每个规则集的结果获取都要用独立的try-catch包装，避免其中一个报错，影响所有结果获取
            try {
                Future<RecRule> future = flow.getFuture();
                RecRule flowResult = future.get(timeoutMilliseconds, TimeUnit.MILLISECONDS);
                mainRecRules.add(flowResult);
            }catch (TimeoutException e){
                e.printStackTrace();
                //规则集线程超时引起的异常不错处理
            }catch (Exception e) {
                e.printStackTrace();

            }
            costTimeForGet += System.currentTimeMillis() - startTimeForGet;
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
