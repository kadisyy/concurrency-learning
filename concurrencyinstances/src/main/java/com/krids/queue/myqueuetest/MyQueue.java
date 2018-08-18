package com.krids.queue.myqueuetest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过list模拟阻塞队列
 */
public class MyQueue {
    private LinkedList<Object> list = new LinkedList<Object>();

    private AtomicInteger conut = new AtomicInteger(0);

    private Integer maxsize;

    MyQueue(Integer maxsize) {
        this.maxsize = maxsize;
    }

    private Object lock = new Object();

    public void put(Object o) {
        synchronized(lock){
            while(conut.get() == this.maxsize){
                try{
                    lock.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(o);

            conut.getAndIncrement();
            System.out.println("元素 " + o + " 已经添加到容器中");
            lock.notify();  //唤醒可能正在等待的take
        }
    }

    public Object take() throws InterruptedException {
        Object o = null;
        synchronized(lock){
            while(conut.get() == 0){
                lock.wait();
            }

            o = list.removeFirst();
            conut.getAndIncrement();
            System.out.println("元素 " + o + " 从容器中移除");
            lock.notify();  // 唤醒可能正在等待的put
        }
        return o;
    }

    public int size() {
        return list.size();
    }
}
