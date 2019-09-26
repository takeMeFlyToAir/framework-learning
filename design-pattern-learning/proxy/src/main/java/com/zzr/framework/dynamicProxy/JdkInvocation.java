package com.zzr.framework.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhaozhirong on 2019/6/12.
 */
public class JdkInvocation implements InvocationHandler {

    private Object object;


    public void setTagServiceObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("TagService代理前");
        Object returnObject = method.invoke(this.object, args);
        System.out.println("TagService代理后");
        return returnObject;
    }
}
