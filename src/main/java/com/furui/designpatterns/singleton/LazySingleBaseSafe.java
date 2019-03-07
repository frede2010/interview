package com.furui.designpatterns.singleton;

/**
 * 线程安全的基本懒汉模式
 * @author furui
 * @date 2019/3/5 0005
 */
public class LazySingleBaseSafe {
    private LazySingleBaseSafe(){}
    private static LazySingleBaseSafe safeLazySingleBase;
    public static synchronized LazySingleBaseSafe getSafeLazySingleBase(){
        if (null == safeLazySingleBase) {
            safeLazySingleBase = new LazySingleBaseSafe();
        }
        return safeLazySingleBase;
    }
}

