package com.zzr.framework.ddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Auther: qiuyujiang
 * @Date: 2019/2/14.
 * @Description: 推荐处理流，依次包括召回 -> 过滤 -> 召回融合 -> 排序 等步骤
 */
public class RecommendationFlow implements Callable<RecRule> {

    private final Logger logger = LoggerFactory.getLogger(RecommendationFlow.class);

    private long time;
    private Future<RecRule> future;

    public RecommendationFlow(long time) {
        this.time = time;
    }

    @Override
    public RecRule call() throws Exception {
        RecRule recRule = new RecRule();
        recRule.setTime(time);
        Thread.sleep(260);
        return recRule;
    }

    public Future<RecRule> getFuture() {
        return future;
    }

    public void setFuture(Future<RecRule> future) {
        this.future = future;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
