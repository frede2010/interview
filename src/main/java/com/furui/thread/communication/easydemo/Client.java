package com.furui.thread.communication.easydemo;

/**
 * @author furui
 * @date 2019/4/8 0008
 */
public class Client {
    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA a = new ThreadA(myList);
        ThreadB b = new ThreadB(myList);
        a.start();
        b.start();
    }
}
