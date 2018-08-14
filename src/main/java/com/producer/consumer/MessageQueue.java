package com.producer.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    public static void main(String[] args) {
        //创建队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

        //创建生产者
        new Thread(new Producer(queue)).start();
        //创建消费者
        new Thread(new Consumer(queue)).start();
    }

}