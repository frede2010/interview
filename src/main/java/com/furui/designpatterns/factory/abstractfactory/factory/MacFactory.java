package com.furui.designpatterns.factory.abstractfactory.factory;

import com.furui.designpatterns.factory.abstractfactory.Digital;
import com.furui.designpatterns.factory.abstractfactory.detail.computer.Mac;
import com.furui.designpatterns.factory.abstractfactory.detail.computer.Surface;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class MacFactory implements DigitalFactory {
    @Override
    public Digital createDigital() {
        return new Mac();
    }
}
