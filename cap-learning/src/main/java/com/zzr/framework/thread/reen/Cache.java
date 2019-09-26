package com.zzr.framework.thread.reen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhaozhirong on 2019/7/9.
 */
public class Cache<K,V> {

    final Map<K,V> cache = new HashMap<K, V>();

    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读锁
    final Lock read = readWriteLock.readLock();

    //写锁
    final Lock write = readWriteLock.writeLock();


    //读缓存
    V get(K key){
        V v = null;
        //读缓存
        read.lock();
        try {
            v=  cache.get(key);
        }finally {
            read.unlock();
        }
        //缓存命中，返回
        if(v != null){
            return v;
        }
        //缓存不命中，查询
        write.lock();
        try {
            //再次验证,其他线程可能已经查询过数据库了
            v = cache.get(key);
            if(v == null){
                v = query();
                cache.put(key, v);
            }
        }finally {
            write.unlock();
        }
        return v;
    }

    //写缓存
    void put(K key, V value){
        write.lock();
        try {
            cache.put(key,value);
        }finally {
            write.unlock();
        }
    }

    V query(){
        V v = null;
        return v;
    }
}
