package com.zzr.framework.singleton;

/**
 * Created by zhaozhirong on 2019/6/4.
 * 饿汉子模式
 * 优点：线程安全
 * 缺点：浪费资源，产生垃圾对象
 */
public class IvoryTower {


    private final static IvoryTower instance = new IvoryTower();

    private IvoryTower(){

    }

    public static IvoryTower getInstance(){
        return instance;
    }

}
