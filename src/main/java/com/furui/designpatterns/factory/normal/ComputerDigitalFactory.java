package com.furui.designpatterns.factory.normal;

import com.furui.designpatterns.factory.normal.detail.DigitalComputer;

/**
 * 电脑工厂类
 * @author furui
 * @date 2019/3/8 0008
 */
public class ComputerDigitalFactory implements DigitalFactory {
    @Override
    public Digital createDigital() {
        return new DigitalComputer();
    }
}
