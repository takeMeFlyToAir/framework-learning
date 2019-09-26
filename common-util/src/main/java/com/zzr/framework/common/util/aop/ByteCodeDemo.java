package com.zzr.framework.common.util.aop;

/**
 * Created by zhaozhirong on 2019/9/24.
 */
public class ByteCodeDemo {

    private int a = 1;

    public int add(){
        int b = 2;
        int c = a + b;
        System.out.println(c);
        return c;
    }

}
