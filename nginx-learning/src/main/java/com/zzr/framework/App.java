package com.zzr.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        siftUpComparable(10);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(3);
        priorityQueue.add(7);
        priorityQueue.add(5);
        priorityQueue.add(10);
        priorityQueue.add(9);
        priorityQueue.add(15);
        priorityQueue.add(11);
        priorityQueue.add(13);
        priorityQueue.add(20);
        priorityQueue.add(12);

        priorityQueue.add(4);
    }

    private static void siftUpComparable(int k) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            k = parent;
            System.out.println(parent);
        }
    }

//    @SuppressWarnings("unchecked")
//    private void siftUpComparable(int k, E x) {
//        Comparable<? super E> key = (Comparable<? super E>) x;
//        while (k > 0) {
//            int parent = (k - 1) >>> 1;
//            Object e = queue[parent];
//            if (key.compareTo((E) e) >= 0)
//                break;
//            queue[k] = e;
//            k = parent;
//        }
//        queue[k] = key;
//    }
}
