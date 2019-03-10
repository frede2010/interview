package com.furui.designpatterns.factory.abstractfactory.detail.computer;

import com.furui.designpatterns.factory.abstractfactory.detail.DigitalComputer;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class Surface extends DigitalComputer {
    /**
     * 特性
     */
    @Override
    public void characteristics() {
        System.out.println("我是微软的surface，生产力工具");
    }

    @Override
    public void function() {
        System.out.println("我在敲代码");
    }
}
