package com.zzr.framework.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhaozhirong on 2019/6/12.
 */
public class Client {
    public static void main(String[] args) {
        JdkInvocation jdkInvocation = new JdkInvocation();
        jdkInvocation.setTagServiceObject(new TagServiceImpl());
        TagService tagService = (TagService) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{TagService.class}, jdkInvocation);
        tagService.printSomeThing();
    }
}
