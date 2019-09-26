package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;
import com.zzr.framework.thread.threadPool.common.Result;
import com.zzr.framework.thread.threadPool.common.TaskRunnable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class SubmitRunnableAndResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Result result = new Result();
        result.setResult("未执行");
        Future<Result> submit = PoolManagerZzr.getPool().submit(new TaskRunnable(result), result);
        System.out.println(submit.get().getResult());
        System.out.println(submit.get() == result);
    }
}
