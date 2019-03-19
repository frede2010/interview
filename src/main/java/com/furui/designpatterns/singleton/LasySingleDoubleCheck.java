package com.furui.designpatterns.singleton;

/**
 * 双重检查下线程安全的懒汉模式
 * @author furui
 * @date 2019/3/5 0005
 */
public class LasySingleDoubleCheck {
    private LasySingleDoubleCheck(){}
    private static LasySingleDoubleCheck instance;
    public static synchronized LasySingleDoubleCheck getInstance(){
        if (instance == null) {
            synchronized (LasySingleDoubleCheck.class){
                if(instance == null){
                    instance = new LasySingleDoubleCheck();
                }
            }
        }
        return instance;
    }
}
