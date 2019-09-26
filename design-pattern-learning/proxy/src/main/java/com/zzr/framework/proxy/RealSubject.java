package com.zzr.framework.proxy;

/**
 * Created by zhaozhirong on 2019/6/11.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("execute request");
    }
}
