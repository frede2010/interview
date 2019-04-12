package com.furui.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在sleep指定的时间内，当调用了lock.lock()方法线程就持有了”对象监视器”，其他线程只能等待锁被释放后再次争抢，效果和使用synchronized关键字是一样的
 * @author furui
 * @date 2019/4/12 0012
 */
public class ReentrantLockDemo2 {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        new Thread(() -> runMethod(lock, 0), "thread1").start();
        new Thread(() -> runMethod(lock, 5000), "thread2").start();
        new Thread(() -> runMethod(lock, 1000), "thread3").start();
        new Thread(() -> runMethod(lock, 5000), "thread4").start();
        new Thread(() -> runMethod(lock, 1000), "thread5").start();
    }

    private static void runMethod(Lock lock, long sleepTime) {
        lock.lock();
        try {
            Thread.sleep(sleepTime);
            System.out.println("ThreadName:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
