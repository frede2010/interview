package com.furui.thread;

/**
 * 工人A
 * @author furui
 * @date 2019/3/5 0005
 */
public class WorkerA extends Worker{
    @Override
    void say(String voice) {
        System.out.println("我是A，我在" + voice);
    }
}
