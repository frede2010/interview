package com.furui.designpatterns.factory.abstractfactory;

import com.furui.designpatterns.factory.abstractfactory.factory.DigitalFactory;
import com.furui.designpatterns.factory.abstractfactory.factory.HuaWeiPhoneFactory;
import com.furui.designpatterns.factory.abstractfactory.factory.SurfaceFactory;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class Client {
    public static void main(String[] args) {

        Digital hwp = new HuaWeiPhoneFactory().createDigital();
        hwp.characteristics();
        hwp.function();

        Digital surface = new SurfaceFactory().createDigital();
        surface.characteristics();
        surface.function();
    }
}
