package com.furui.designpatterns.factory.normal.detail;

import com.furui.designpatterns.factory.normal.Digital;

/**
 * 电脑产品
 * @author furui
 * @date 2019/3/8 0008
 */
public class DigitalComputer implements Digital {
    /**
     * 特性
     */
    @Override
    public void characteristics() {
        System.out.println("我是电脑，可以开发程序");
    }

    @Override
    public void function() {
        System.out.println("我在启动IDEA");
    }
}
