package com.furui.designpatterns.singleton;

/**
 * 懒汉单例（线程非安全）
 * @author furui
 * @date 2019/3/5 0005
 */
public class LazySingleBase {
    private LazySingleBase(){}

    private static LazySingleBase lazySingleBase;

    public static LazySingleBase getInstance(){
        if (null == lazySingleBase){
            lazySingleBase = new LazySingleBase();
        }
        return lazySingleBase;
    }
}
