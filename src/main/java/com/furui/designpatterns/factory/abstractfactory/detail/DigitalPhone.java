package com.furui.designpatterns.factory.abstractfactory.detail;

import com.furui.designpatterns.factory.abstractfactory.Digital;

/**
 * 手机产品
 * @author furui
 * @date 2019/3/8 0008
 */
public class DigitalPhone implements Digital {
    /**
     * 特性
     */
    @Override
    public void characteristics() {
        System.out.println("我是手机，我可以方便人们的沟通");
    }

    @Override
    public void function() {
        System.out.println("我在打电话给小王");
    }
}
