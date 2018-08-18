package com.krids.queue.myqueuetest;

public class TestMyQueue {
    public static void main(String[] args) throws InterruptedException {
        final MyQueue myQueue = new MyQueue(5);

        myQueue.put(1);
        myQueue.put(2);
        myQueue.put(3);
        myQueue.put(4);
        myQueue.put(5);

        System.out.println("当前元素的个数： " + myQueue.size());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put(6);
            }
        });
        t1.start();

        System.out.println("当前元素的个数： " + myQueue.size());
        myQueue.take();
        Thread.sleep(100L);
        System.out.println("当前元素的个数： " + myQueue.size());


    }
}
