package com.zzr.framework.thread.semaphore;

/**
 * Created by zhaozhirong on 2019/7/9.
 */
public class Counter {

    private int count;

    public int addOne(){
        return ++count;
    }

    public int getCount(){
        return count;
    }

}
