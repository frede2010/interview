package com.furui.lock.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写互斥
 * @author furui
 * @date 2019/4/12 0012
 */
public class ReadAndWrite {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ReadAndWrite demo = new ReadAndWrite();

        new Thread(() -> demo.read(), "ThreadA").start();
        new Thread(() -> demo.write(), "ThreadB").start();
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
    private void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName()
                        + " 时间:" + System.currentTimeMillis());
                //模拟读操作时间为5秒
                Thread.sleep(5000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
