# concurrency-learning

记录并行开发点滴

## 生产者消费者

针对ArrayBlockQueue, 队列满, 则put方法阻塞, 反之take阻塞.

多线程执行的时，如果使用volatile，可以进行通信等
