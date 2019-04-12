package com.furui.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author furui
 * @date 2019/4/12 0012
 */
public class SynchronizedDemo2 {
    public static void main(String[] args) {

        new Thread(() -> runMethod(0), "thread1").start();
        new Thread(() -> runMethod(5000), "thread2").start();
        new Thread(() -> runMethod(1000), "thread3").start();
        new Thread(() -> runMethod(5000), "thread4").start();
        new Thread(() -> runMethod(1000), "thread5").start();
    }

    private static synchronized void runMethod(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
            System.out.println("ThreadName:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
