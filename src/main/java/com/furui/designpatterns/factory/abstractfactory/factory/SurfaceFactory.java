package com.furui.designpatterns.factory.abstractfactory.factory;

import com.furui.designpatterns.factory.abstractfactory.Digital;
import com.furui.designpatterns.factory.abstractfactory.detail.DigitalComputer;
import com.furui.designpatterns.factory.abstractfactory.detail.DigitalPhone;
import com.furui.designpatterns.factory.abstractfactory.detail.computer.Surface;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class SurfaceFactory implements DigitalFactory {
    @Override
    public Digital createDigital() {
        return new Surface();
    }
}
