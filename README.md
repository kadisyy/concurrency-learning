# concurrency-learning

记录并行开发点滴

## 生产者消费者

针对ArrayBlockQueue, 队列满, 则put方法阻塞, 反之take阻塞.

多线程执行的时，如果使用volatile，可以进行通信等

## lock与synchronized 
synchronized能够把任何一个非null对象当成锁，实现由两种方式：
　　a.当synchronized作用于非静态方法时，锁住的是当前对象的事例，当synchronized作用于静态方法时，锁住的是class实例，又因为Class的相关数据存储在永久带，因此静态方法锁相当于类的一个全局锁。

　　b.当synchronized作用于一个对象实例时，锁住的是对应的代码块。

　　2.synchronized锁又称为对象监视器（object）。
    3.当多个线程一起访问某个对象监视器的时候，对象监视器会将这些请求存储在不同的容器中。

```
public void test() {
     ...
     synchronized(this) {
          // todo your code
     }
     ...
}
 
此时，其效果等同于
public synchronized void test() {
     // todo your code
}
```

synchronized是在JVM层面实现的，因此系统可以监控锁的释放与否，而ReentrantLock使用代码实现的，系统无法自动释放锁，需要在代码中finally子句中显式释放锁lock.unlock();
总之:
1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；

2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；

3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；

4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。

5）Lock可以提高多个线程进行读操作的效率。

## 可重入锁
synchronized和ReentrantLock都是可重入锁
如下:
```
class MyClass {
    public synchronized void method1() {
        method2();
    }
     
    public synchronized void method2() {
         
    }
}
```

上述代码中的两个方法method1和method2都用synchronized修饰了，假如某一时刻，线程A执行到了method1，此时线程A获取了这个对象的锁，而由于method2也是synchronized方法，假如synchronized不具备可重入性，此时线程A需要重新申请锁。但是这就会造成一个问题，因为线程A已经持有了该对象的锁，而又在申请获取该对象的锁，这样就会线程A一直等待永远不会获取到的锁。
而由于synchronized和Lock都具备可重入性，所以不会发生上述现象。


