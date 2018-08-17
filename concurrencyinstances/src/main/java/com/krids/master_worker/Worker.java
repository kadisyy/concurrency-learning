package com.krids.master_worker;

import java.util.concurrent.*;

/**
 * 接受master发过来的任务
 */
public class Worker implements Runnable {
    private static ExecutorService service = Executors.newFixedThreadPool(10);

    @Override
    public void run() {
        Job job;
        while(true){

            if(CommonUtils.getInstanceQueue().size() > 0) {
                job = CommonUtils.getInstanceQueue().poll();
                try{
                    System.out.println("result = " + service.submit(job).get(1, TimeUnit.SECONDS).toString());
                    CommonUtils.getInstanceMap().putIfAbsent(Thread.currentThread().getName(), service.submit(job).get(1, TimeUnit.SECONDS).toString());
                    //Doing SomeThing
                    Thread.sleep(1000L);
                    System.out.println("ending");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("waiting");
            }
            try{
                Thread.sleep(1000L);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
