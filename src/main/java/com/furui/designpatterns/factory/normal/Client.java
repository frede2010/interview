package com.furui.designpatterns.factory.normal;

/**
 * 调用者无需知道具体的类名以及实现细节
 * @author furui
 * @date 2019/3/8 0008
 */
public class Client {
    public static void main(String[] args) {

        Digital cp = new ComputerDigitalFactory().createDigital();
        cp.characteristics();
        cp.function();

        Digital pp = new PhoneDigitalFactory().createDigital();
        pp.characteristics();
        pp.function();
    }
}
