package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;

import java.util.concurrent.*;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class SubmitRunnable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future submit = PoolManagerZzr.getPool().submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute task");
            }
        });
        submit.get();
        if(submit.isDone()){
            System.out.println("end task");
        }
        System.out.println("main execute");
    }

}
