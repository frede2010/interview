package com.furui.designpatterns.factory.abstractfactory.factory;

import com.furui.designpatterns.factory.abstractfactory.Digital;
import com.furui.designpatterns.factory.abstractfactory.detail.DigitalPhone;
import com.furui.designpatterns.factory.abstractfactory.detail.phone.IPhone;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public class IPhoneFactory implements DigitalFactory {
    @Override
    public Digital createDigital() {
        return new IPhone();
    }
}
