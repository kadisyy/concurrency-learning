package com.krids.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        concurrentLinkedQueue.offer(8);

        System.out.println("peek value = " + concurrentLinkedQueue.peek());

        System.out.println("after peek size = " + concurrentLinkedQueue.size());

        System.out.println("poll value = " +concurrentLinkedQueue.poll());

        System.out.println("after poll size = " + concurrentLinkedQueue.size());

        System.out.println("after size 0, and take = " + concurrentLinkedQueue.peek());

        System.out.println("##################");

        //结论： 不会阻塞，即使没数据。

    }
}
