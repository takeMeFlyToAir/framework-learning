package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class FutureTaskResultDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Result result = new Result();
        FutureTask<Result> stringFutureTask = new FutureTask<Result>(runnable,result);

    }

}
