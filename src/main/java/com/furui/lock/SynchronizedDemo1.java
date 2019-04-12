package com.furui.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author furui
 * @date 2019/4/12 0012
 */
public class SynchronizedDemo1 {
    public static void main(String[] args) {

        //lambda写法
        new Thread(() -> runMethod(), "thread1").start();
        new Thread(() -> runMethod(), "thread2").start();
        new Thread(() -> runMethod(), "thread3").start();
        new Thread(() -> runMethod(), "thread4").start();
        //常规写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                runMethod();
            }
        }, "thread5").start();
    }

    private synchronized static void runMethod() {
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(100);
                System.out.println("ThreadName:" + Thread.currentThread().getName() + (" i=" + i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
