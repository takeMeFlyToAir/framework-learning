package com.zzr.framework.thread.threadPool.common;

public class TaskRunnable implements Runnable{
        Result result;
        public TaskRunnable(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            System.out.println(result.getResult());
            System.out.println("执行");
            result.setResult("已执行");
        }
    }