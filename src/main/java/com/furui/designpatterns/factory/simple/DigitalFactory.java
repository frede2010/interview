package com.furui.designpatterns.factory.simple;

import com.furui.designpatterns.factory.simple.detail.DigitalComputer;
import com.furui.designpatterns.factory.simple.detail.DigitalPhone;

/**
 * 工程类
 * @author furui
 * @date 2019/3/8 0008
 */
public class DigitalFactory {
    public static Digital createDigital(DigitalEnum product){
        switch (product){
            case PHONE :
                return new DigitalPhone();
            case COPMPUTER :
                return new DigitalComputer();
            default :
                return null;
        }

    }
}
