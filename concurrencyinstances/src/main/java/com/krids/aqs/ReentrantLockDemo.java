package com.krids.aqs;


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    // public ReentrantLock(boolean fair) { sync = fair ? new FairSync() : new NonfairSync();}
    // 本质是一个工厂方法,根据入参fail,构建不同的对象. 默认不传参数是非公平
    ReentrantLock reentrantLock = new ReentrantLock();

    // 非公平:  调用Lock加锁的时候,直接cas 设置state, 如果state !=1, 则认为是重入锁.
    public void method(){
        reentrantLock.lock();

        System.out.println("开始加锁");

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
        System.out.println("释放锁");

    }
    public static void main(String[] args) {
        final ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                demo.method();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                demo.method();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
