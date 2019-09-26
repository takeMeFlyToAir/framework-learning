package com.zzr.framework.singleton;

/**
 * Created by zhaozhirong on 2019/6/4.
 */
public class Holder {
    private static class SingletonHolder{
        private static final Holder instance = new Holder();
    }
    private Holder(){

    }
    public static final Holder getInstance(){
        return SingletonHolder.instance;
    }
}
