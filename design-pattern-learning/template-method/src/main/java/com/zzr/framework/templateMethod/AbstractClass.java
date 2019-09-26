package com.zzr.framework.templateMethod;

/**
 * Created by zhaozhirong on 2019/6/11.
 */
public abstract class AbstractClass {

    protected abstract void doSomething();

    protected abstract void doAnything();

    public final void templateMethod(){
        doSomething();
        doAnything();
    }

}
