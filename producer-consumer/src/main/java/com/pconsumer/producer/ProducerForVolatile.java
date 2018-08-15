package com.pconsumer.producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerForVolatile implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<String> queue;
    private static final int DEFAULT_SLEEP = 1000;
    private static AtomicInteger count = new AtomicInteger();

    public ProducerForVolatile(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String data = null;
        Random r = new Random();

        System.out.println("启动生产者线程！");
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(DEFAULT_SLEEP));
                data = "data:" + count.incrementAndGet();
                queue.put(data);
                System.out.println("将数据：" + data + "放入队列...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }

}