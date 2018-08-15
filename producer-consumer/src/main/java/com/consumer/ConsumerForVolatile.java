package com.consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ConsumerForVolatile implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<String> queue;
    private static final int DEFAULT_SLEEP = 1000;

    public ConsumerForVolatile(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("启动消费者线程！");
        Random r = new Random();
        try {
            while (isRunning) {
                String data = queue.take();
                if (null != data) {
                    System.out.println("正在消费：" + data);
                    Thread.sleep(r.nextInt(DEFAULT_SLEEP));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出消费者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }

}