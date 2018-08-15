package com.pconsumer.MainPkg;

import com.pconsumer.consumer.ConsumerForVolatile;
import com.pconsumer.producer.ProducerForVolatile;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MainTest {

	public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

		ProducerForVolatile producer1 = new ProducerForVolatile(queue);
        ProducerForVolatile producer2 = new ProducerForVolatile(queue);
        ProducerForVolatile producer3 = new ProducerForVolatile(queue);
		ConsumerForVolatile consumer = new ConsumerForVolatile(queue);

		// 借助Executors
		ExecutorService service = Executors.newCachedThreadPool();
		// 启动线程
		service.execute(producer1);
		service.execute(producer2);
		service.execute(producer3);
		service.execute(consumer);

		// 执行20s
		Thread.sleep(1000);
		producer1.stop();

        //停止之后，就不进行继续消费
		producer2.stop();
		producer3.stop();

		Thread.sleep(2 * 1000);
		consumer.stop();
		// 退出Executor
		service.shutdown();
	}

}