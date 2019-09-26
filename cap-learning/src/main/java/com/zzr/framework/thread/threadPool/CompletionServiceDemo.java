package com.zzr.framework.thread.threadPool;

import com.zzr.framework.thread.threadPool.common.PoolManagerZzr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhaozhirong on 2019/7/11.
 */
public class CompletionServiceDemo {
    public static void main(String[] args) throws InterruptedException {

        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(PoolManagerZzr.getPool());

        List<Future<Integer>> futures = new ArrayList<>();

        futures.add(
                cs.submit(() -> getByS1())
        );
        futures.add(
                cs.submit(() -> getByS2())
        );
        futures.add(
                cs.submit(() -> getByS3())
        );
        Integer r = 0;

        try {
            for (int i = 0; i < 3; i++) {
                r = cs.take().get();

                if(r != null){
                    break;
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            for (Future<Integer> f : futures){
                f.cancel(true);
            }
        }

        System.out.println(r);

    }

    private static Integer getByS1(){
        sleep(2,TimeUnit.SECONDS);
        return 1;
    }
    private static Integer getByS2(){
        sleep(4,TimeUnit.SECONDS);
        return 2;
    }
    private static Integer getByS3(){
        sleep(1,TimeUnit.SECONDS);
        return 3;
    }

    private static void sleep(int n, TimeUnit timeUnit){
        try {
            timeUnit.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
