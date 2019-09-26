package com.zzr.framework.thread.threadPool.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class PoolManagerZzr {
    private final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,100,0, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(),new CustomThreadFactory());

    public static ExecutorService getPool(){
        return poolExecutor;
    }
}
