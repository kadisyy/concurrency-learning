package com.krids.countdowntest;

import java.util.concurrent.CountDownLatch;
 
/**
 * 比如一个团队赛跑游戏，最后要计算团队赛跑的成绩，主线程计算最后成绩，要等到所有
 * 团队成员跑完，方可计算总成绩。
 * 
 * 题目2：统计4个盘子的大小，统计完后交给第五个人汇总
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(4);
		
		
		//四个人赛跑,这4个可以写一个for循环
		new Thread(new Worker(startSignal, doneSignal)).start();
		new Thread(new Worker(startSignal, doneSignal)).start();
		new Thread(new Worker(startSignal, doneSignal)).start();
		new Thread(new Worker(startSignal, doneSignal)).start();
 
		System.out.println("这里放计数前自己的代码...");//由于startSignal是1，上边的4个线程还不能执行
		startSignal.countDown(); // startSignal=0，线程可以开始执行了
		
		//doSomethingElse();
		System.out.println("计数后的代码...这是主线程...和上边4个线程混在一起的...");
		doneSignal.await(); // 判断上边4个是否执行完了，如果是，则允许下边的代码执行
		//开始执行另外一个任务
		System.out.println("开始执行其他任务...");
	}
}
