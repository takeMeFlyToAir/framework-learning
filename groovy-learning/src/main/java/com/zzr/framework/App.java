package com.zzr.framework;


/**
 * Hello world!
 * *  -verbose:gc -Xmx20m  -Xms20m -Xmn10M -XX:+PrintGCDetails  -XX:SurvivorRatio=8 -XX:+PrintGCTimeStamps

 *
 */
public class App {

    private static final int _1MB = 1024 * 1024;

    public static void main( String[] args ) throws Exception {
       byte[] a1,a2,a3,a4;
       a1 = new byte[2 * _1MB];
       a2 = new byte[2 * _1MB];
       a3 = new byte[2 * _1MB];
       a4 = new byte[4 * _1MB];
    }
}
