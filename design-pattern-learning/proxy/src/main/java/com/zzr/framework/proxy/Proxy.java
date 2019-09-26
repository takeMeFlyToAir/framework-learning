package com.zzr.framework.proxy;

/**
 * Created by zhaozhirong on 2019/6/11.
 */
public class Proxy implements Subject {

    private Subject subject = null;

    public Proxy() {
        this.subject = new RealSubject();
    }

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        this.subject.request();
        after();
    }

    private void before(){

    }

    private void after(){

    }
}
