package com.krids.countdowntest;

import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
 
	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}
 
	@Override
	public void run() {
		try {
			startSignal.await();  //startSignal的计数到0了吗？到0了，才能执行
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} 
	}
 
	void doWork() {
		System.out.println("正在统计中...");
	}
}
