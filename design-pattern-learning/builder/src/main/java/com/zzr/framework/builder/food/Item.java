package com.zzr.framework.builder.food;

import com.zzr.framework.builder.pack.Packing;

/**
 * Created by zhaozhirong on 2019/6/6.
 */
public interface Item {

    String name();
    Packing packing();
    float price();
}
