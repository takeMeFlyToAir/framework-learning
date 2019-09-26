package com.zzr.framework.thread.threadPool.common;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class CustomThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,"自定义");
        return thread;
    }
}
