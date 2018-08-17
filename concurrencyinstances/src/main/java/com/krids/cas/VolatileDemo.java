package com.krids.cas;

import java.util.concurrent.*;

class MethodDemo implements Runnable{
    public  volatile Integer count = 0;

    @Override
    public synchronized void run() {
        count ++;
        System.out.println("count value = " + count);
        System.out.println("name = " + Thread.currentThread().getName() );
    }
}
public class VolatileDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
       MethodDemo methodDemo = new MethodDemo();
        for(int i = 0 ; i < 100 ; i++){
            service.submit(methodDemo);
        }
        service.shutdown();
    }
}
