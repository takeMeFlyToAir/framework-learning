package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;

import java.util.concurrent.*;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class Execute {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        PoolManagerZzr.getPool().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute task");
            }
        });
        System.out.println("main execute");
    }

}
