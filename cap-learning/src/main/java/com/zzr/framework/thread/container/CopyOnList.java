package com.zzr.framework.thread.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhaozhirong on 2019/7/10.
 */
public class CopyOnList {

    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(2);
        Iterator iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        List list = new ArrayList();
        Iterator iterator1 = list.iterator();
        while (iterator1.hasNext()){
            iterator1.remove();
        }
    }

}
