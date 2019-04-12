package com.furui.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当前线程打印完毕之后释放锁，其他线程才可以获取锁然后进行打印。线程打印的数据是分组打印的，这是因为当前线程已经持有锁，在当前线程打印完之后才会释放锁，但线程之间打印的顺序是随机的
 * @author furui
 * @date 2019/4/12 0012
 */
public class ReentrantLockDemo1 {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        //lambda写法
        new Thread(() -> runMethod(lock), "thread1").start();
        new Thread(() -> runMethod(lock), "thread2").start();
        new Thread(() -> runMethod(lock), "thread3").start();
        new Thread(() -> runMethod(lock), "thread4").start();
        //常规写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                runMethod(lock);
            }
        }, "thread5").start();
    }

    private static void runMethod(Lock lock) {
        lock.lock();
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(100);
                System.out.println("ThreadName:" + Thread.currentThread().getName() + (" i=" + i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println();

    }
}
