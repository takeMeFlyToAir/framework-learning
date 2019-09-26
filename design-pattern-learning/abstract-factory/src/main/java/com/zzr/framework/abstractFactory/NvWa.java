package com.zzr.framework.abstractFactory;

/**
 * Created by zhaozhirong on 2019/6/10.
 */
public class NvWa {
    public static void main(String[] args) {
        AbstractHumanFactory yinYangLu = new HumanFactory();

        WhiteHuman whiteHuman = yinYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
    }
}
