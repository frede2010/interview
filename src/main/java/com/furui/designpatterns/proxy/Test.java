package com.furui.designpatterns.proxy;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public class Test {
    /**
     * 动态代理 需要接口和接口实现
     * @param args
     */
    public static void main(String[] args) {
        DynamicProxy proxy = new DynamicProxy();
        MyService myService = (MyService)proxy.getInstance(new MyServiceImpl());
        myService.insert();
    }
}
