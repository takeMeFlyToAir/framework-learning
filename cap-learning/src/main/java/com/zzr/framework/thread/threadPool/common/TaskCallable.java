package com.zzr.framework.thread.threadPool.common;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Result> {

        private Result result;

        public TaskCallable(Result result) {
            this.result = result;
        }

        @Override
        public Result call() throws Exception {
            System.out.println(result.getResult());
            System.out.println("执行");
            result.setResult("已执行");
            return result;
        }
    }