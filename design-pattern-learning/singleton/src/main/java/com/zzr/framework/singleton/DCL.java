package com.zzr.framework.singleton;

/**
 * Created by zhaozhirong on 2019/6/4.
 */
public class DCL {

    private static volatile DCL instance;

    private DCL(){

    }

    public static DCL getInstance(){
        if(instance == null){
            synchronized (DCL.class){
                if(instance == null){
                    instance = new DCL();
                }
            }
        }
        return instance;
    }

}
