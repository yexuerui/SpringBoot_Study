package Algorithm.future;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedAndBuffer {

    //公共资源
    final Lock lock=new ReentrantLock();
    //生产者进程
    final Condition notFull=lock.newCondition();
    //消费者进程
    final Condition notEmpty=lock.newCondition();

    //缓存队列
    final Object[] items=new Object[100];
    int putptr=0;
    int takeptr=0;
    int count=0;
    //logger
    final Logger logger= LoggerFactory.getLogger(this.getClass());
    //生产消息
    public  void put(Object obj) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        logger.info("生产者开始 ： "+obj);
        lock.lock();
        try {
            while (count == items.length)
                //如果队列满了阻塞写线程.写线程会释放lock,写线程接收到signal时需要重新获得lock
                notFull.await();
            //赋值
            items[putptr] = obj;
            if (++putptr == items.length)
                //如果写索引写到队列的最后一个位置了，那么重置为0,开启下一轮
                putptr = 0;
            //队列中已存在的元素个数加1
            ++count;
            //发出非空signal,等待非空signal的线程会被唤醒
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                //如果队列为空,等待非空signal.释放lock,接收到signal后需要先获得lock
                notEmpty.await();
            Object x = items[takeptr];// 取值
            if (++takeptr == items.length)
                //如果读索引读到队列的最后一个位置了，那么重置为0
                takeptr = 0;
            //队列中已存在的元素个数减1
            --count;//
            //发出condition队列不满signal,等待该signal的线程继续执行.
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }


}
