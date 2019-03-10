package com.furui.designpatterns.factory.normal;

import com.furui.designpatterns.factory.normal.detail.DigitalPhone;

/**
 * 手机工厂类
 * @author furui
 * @date 2019/3/8 0008
 */
public class PhoneDigitalFactory implements DigitalFactory {
    @Override
    public Digital createDigital() {
        return new DigitalPhone();
    }
}
