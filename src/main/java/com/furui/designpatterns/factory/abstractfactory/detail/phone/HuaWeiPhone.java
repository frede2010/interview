package com.furui.designpatterns.factory.abstractfactory.detail.phone;

import com.furui.designpatterns.factory.abstractfactory.detail.DigitalPhone;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class HuaWeiPhone extends DigitalPhone {
    /**
     * 特性
     */
    @Override
    public void characteristics() {
        System.out.println("我是华为手机，中国芯");
    }

    @Override
    public void function() {
        System.out.println("我在运行游戏");
    }
}
