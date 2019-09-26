package com.zzr.framework.thread.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * Created by zhaozhirong on 2019/7/9.
 */
public class ObjPool<T, R> {

    final List<T> pool;

    final Semaphore semaphore;

    public ObjPool(int size, T t) {
        pool = new Vector<T>(size);
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    R exec(Function<T,R> function){
        T t = null;
        R result = null;
        try {
            semaphore.acquire();
            t = pool.remove(0);
            result = function.apply(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pool.add(t);
            semaphore.release();
        }
        return result;
    }

    public static void main(String[] args) {
        ObjPool<Counter,Integer> pool = new ObjPool<Counter,Integer>(10,new Counter());
        System.out.println(pool.exec(Counter::addOne));
    }
}
