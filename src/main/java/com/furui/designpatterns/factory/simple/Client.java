package com.furui.designpatterns.factory.simple;

/**
 * 调用者无需知道具体的类名以及实现细节
 * @author furui
 * @date 2019/3/8 0008
 */
public class Client {
    public static void main(String[] args) {
        Digital cp = DigitalFactory.createDigital(DigitalEnum.COPMPUTER);
        cp.characteristics();
        cp.function();

        Digital pp = DigitalFactory.createDigital(DigitalEnum.PHONE);
        pp.characteristics();
        pp.function();
    }
}
