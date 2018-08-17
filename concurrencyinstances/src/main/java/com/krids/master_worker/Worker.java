package com.krids.master_worker;

import java.util.concurrent.*;

/**
 * 接受master发过来的任务
 */
public class Worker {
    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        Job job;
        while(true){
            System.out.println("waiting");
            if(CommonUtils.getInstanceQueue().size() > 0) {
                job = CommonUtils.getInstanceQueue().poll();
                try{
                    CommonUtils.getInstanceMap().putIfAbsent(Thread.currentThread().getName(), service.submit(job).get(3, TimeUnit.SECONDS).toString());
                    Thread.sleep(1000L);
                    System.out.println("ending");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(5000L);
        }


    }
}
