package com.wangheng.thread;

/**
 * @author wangheng
 * time 20190623
 * synchronized关键字
 */
public class TestSynch {
    Thread t = new Thread() {
        public void run() {
            System.out.println("1");
        }
    };

    public void test() {
        t.run();
        System.out.println("2");
    }

    synchronized void testSyn() {
        System.out.println("a");
    }

    void testSynT() {
        synchronized (this) {
            System.out.println("b");
        }
    }

    public static void main(String[] args) {
        //会不会死锁，输出是什么？
        TestSynch testSynch = new TestSynch();
        testSynch.test();
        new Thread() {
            public void run() {
                testSynch.testSyn();
                testSynch.testSynT();
            }
        };
    }

}
