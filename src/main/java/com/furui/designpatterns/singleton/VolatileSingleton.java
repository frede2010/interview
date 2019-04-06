package com.furui.designpatterns.singleton;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title VolatileSingleton
 * @Descripion 终极版本：volatile
 * @date 2019/3/19 13:28
 */
public class VolatileSingleton {
    private static  volatile VolatileSingleton volatileSingleton;
    private VolatileSingleton(){

    }
    public static VolatileSingleton getInstance(){
        if(volatileSingleton == null){
            synchronized (VolatileSingleton.class){
                if(volatileSingleton == null){
                    volatileSingleton = new VolatileSingleton();
                }
            }
        }
        return volatileSingleton;
    }
}
