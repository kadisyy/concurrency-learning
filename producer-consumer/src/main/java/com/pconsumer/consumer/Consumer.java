package com.pconsumer.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Consumer implements Runnable {

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(12);
        //创建12个消费者
        for(int i=0;i<4;i++){
            Future<String> future = service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String element = queue.take();
                    return "消费者消费："+element;
                }
            });
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}