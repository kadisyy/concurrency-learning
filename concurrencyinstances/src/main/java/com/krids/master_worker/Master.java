package com.krids.master_worker;

import jdk.nashorn.internal.scripts.JO;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class GetResult implements Runnable {

    @Override
    public void run() {
        //get result
        System.out.println("get result");
        while(true){
            if(CommonUtils.getInstanceMap().size() > 0) {
                for(String key : CommonUtils.getInstanceMap().keySet()) {
                    System.out.println("key = " + key + ", value = " + CommonUtils.getInstanceMap().get(key));
                }
            }
        }
    }
}

class MakeJob implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i <= 10; i++) {
            Job job = new Job("message index = " + i, "file-" + i + ".txt");
            CommonUtils.getInstanceQueue().offer(job);
            System.out.println("make success, job id = " + i);
            try{
                Thread.sleep(1000L);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Master {
    //push job into queue
    public static void main(String[] args) throws InterruptedException {
        MakeJob makeJob = new MakeJob();
        GetResult getResult = new GetResult();
        Worker worker = new Worker();

        //开启三个线程
        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(makeJob);
        Thread t3 = new Thread(getResult);

        t2.start();
        t1.start();
        t1.join();
        t3.start();
    }
}
