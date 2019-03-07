package com.furui.thread;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        ThreadA a = new ThreadA(lock);
        a.start();

        //NotifyThread notifyThread = new NotifyThread(lock);
        // notifyThread.start();代理

        SynNotifyMethodThread c = new SynNotifyMethodThread(lock);
        c.start();
    }
}
