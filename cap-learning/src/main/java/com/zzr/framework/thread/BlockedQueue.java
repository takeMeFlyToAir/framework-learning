package com.zzr.framework.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhaozhirong on 2019/7/4.
 */
public class BlockedQueue<T> {

    final Lock lock = new ReentrantLock(true);
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Boolean empty = true;
    final Boolean full = true;
    /**
     * 入队
     * @param t
     */
    public void put(T t){
        lock.lock();
        try {
            while (full){
                notFull.await();
            }
            enqueue(t);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(T t){

    }

    public void take(T t){
        lock.lock();
        try {
            while (empty){
                notEmpty.await();
            }
            dequeue(t);
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dequeue(T t){

    }
}
