package com.zzr.framework.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by zhaozhirong on 2019/7/9.
 */
public class SemaphoreDemo {

    static int count;
    static final Semaphore s = new Semaphore(1);

    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count += 1;
        }finally {
            s.release();
        }
    }

}
