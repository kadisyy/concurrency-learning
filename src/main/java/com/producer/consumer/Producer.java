package com.producer.consumer;

import java.util.concurrent.*;

public class Producer implements Runnable {

    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //创建10个生产者
        for (int i = 0; i < 20; i++) {
            Future<String> future = service.submit(new Callable<String>() {
                public String call() throws Exception {
                    String threadName = Thread.currentThread().getName();
                    queue.put(threadName + "：一条消息");
                    System.out.printf("size = " +String.valueOf(queue.size()));
                    return threadName + "生产了一条消息";
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

