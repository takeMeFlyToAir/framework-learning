package com.zzr.framework.abstractFactory;

/**
 * Created by zhaozhirong on 2019/6/10.
 */
public abstract class AbstractHumanFactory {

    public abstract <T extends Human> T createHuman(Class<T> c);

}
