package com.wangheng.grammer.testStatic;

/**
 * @author wangheng
 * time 20190623
 */
public class TestStaticB {
    {
        System.out.println("111");
    }
    static {
        System.out.println("222");
    }
    TestStaticB(){
        System.out.println("333");
    }
}
