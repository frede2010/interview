package com.furui.lock.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读共享
 * @author furui
 * @date 2019/4/12 0012
 */
public class ReadAndRead {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ReadAndRead demo = new ReadAndRead();

        new Thread(() -> demo.read(), "ThreadA").start();
        new Thread(() -> demo.read(), "ThreadB").start();
    }

    private void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName()
                        + " 时间:" + System.currentTimeMillis());
                //模拟读操作时间为5秒
                Thread.sleep(5000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
