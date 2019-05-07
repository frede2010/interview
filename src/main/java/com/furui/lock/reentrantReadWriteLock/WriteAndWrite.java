package com.furui.lock.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写写互斥
 * @author furui
 * @date 2019/4/12 0012
 */
public class WriteAndWrite {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        WriteAndWrite demo = new WriteAndWrite();

        new Thread(() -> demo.write(), "ThreadA").start();
        new Thread(() -> demo.write(), "ThreadB").start();
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
