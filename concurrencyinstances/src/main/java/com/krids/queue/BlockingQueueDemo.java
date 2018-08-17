package com.krids.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        try{
            //会一直阻塞
            //System.out.println(blockingQueue.take());
            //超过10s 继续执行
            //System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));

            blockingQueue.offer(1);
            blockingQueue.offer(2);
            blockingQueue.offer(3);
            //if queue is full, use offer return false directly
            blockingQueue.offer(4);

            //一直阻塞，下面一句不会执行
            blockingQueue.put(5);

            System.out.println("########");
//            while(blockingQueue.size() >= 0){
//                //实际打印1，2，3 然后一直阻塞
//                System.out.println( blockingQueue.take());
//                System.out.println("循环：" + blockingQueue.size());
//            }
            while(blockingQueue.size() >= 0){
                //实际打印1，2，3 然后10后继续执行
                System.out.println( blockingQueue.poll(10,TimeUnit.SECONDS));
                System.out.println("循环：" + blockingQueue.size());
            }

        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
