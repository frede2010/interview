package com.furui.designpatterns.factory.abstractfactory.detail.computer;

import com.furui.designpatterns.factory.abstractfactory.detail.DigitalComputer;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class Mac extends DigitalComputer {
    /**
     * 特性
     */
    @Override
    public void characteristics() {
        System.out.println("我是苹果系列的Mac，很多UI设计很喜欢我");;
    }

    @Override
    public void function() {
        System.out.println("我在设计室内装修图");
    }
}
