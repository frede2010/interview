package com.furui.designpatterns.proxy;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public class MyServiceImpl implements MyService {
    @Override
    public void insert() {
        System.out.println("插入成功！");
    }
}
