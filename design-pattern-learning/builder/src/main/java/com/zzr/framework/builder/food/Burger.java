package com.zzr.framework.builder.food;

import com.zzr.framework.builder.pack.Packing;
import com.zzr.framework.builder.pack.Wrapper;

/**
 * Created by zhaozhirong on 2019/6/6.
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
