package com.wangheng.grammer.testStatic;

/**
 * @author wangheng
 * time 20190623
 * static静态块
 */
public class TestStaticA extends TestStaticB {

    {
        System.out.println("444");
    }

    static {
        System.out.println("555");
    }

    TestStaticA() {
        System.out.println("666");
    }

    public static void main(String[] args) {
        System.out.println("start");
        new TestStaticA();
    }
}
