package com.zzr.framework.abstractFactory;

/**
 * Created by zhaozhirong on 2019/6/10.
 */
public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白的！");
    }

    @Override
    public void talk() {
        System.out.println("白人会说话，一般都是单字节");
    }
}
