package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;
import com.zzr.framework.thread.threadPool.common.Result;
import com.zzr.framework.thread.threadPool.common.TaskCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Result result = new Result();
        result.setResult("未执行");
        TaskCallable task = new TaskCallable(result);
        Future<Result> resultFuture = PoolManagerZzr.getPool().submit(task);
        System.out.println(resultFuture.get().getResult());
    }
}
