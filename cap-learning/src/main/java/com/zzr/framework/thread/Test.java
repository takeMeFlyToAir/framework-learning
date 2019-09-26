package com.zzr.framework.thread;

import com.zzr.framework.thread.threadPool.PoolManager;
import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;


public class Test {
    public static void main(String[] args) throws InterruptedException {
        PoolManager poolManager = new PoolManager();
        poolManager.getSendMessagePool().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(1);
                    Thread.sleep(2000);
                    System.out.println(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(3);
        poolManager.getSendMessagePool().submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(4);
            }
        });
        poolManager.getSendMessagePool().submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(5);
            }
        });
        System.out.println(6);
    }

    public static void test1() throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("3");
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        Thread.interrupted();
        System.out.println(33);
    }

    public static void test(){
        final AtomicInteger count = new AtomicInteger(0);
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    LockSupport.park();
                    Thread.interrupted();//clear interrupt flag
                }
            }
        });

        for(int i =0;i<1;i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (!thread.isInterrupted()) {
                            thread.interrupt();
                            count.incrementAndGet();
                        }
                    }
                }
            }).start();
        }
        new Thread(new Runnable() {
            public void run() {
                long last = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(String.format("thread park %d times in 1s", count.get() - last));
                        last = count.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        thread.start();
    }
}