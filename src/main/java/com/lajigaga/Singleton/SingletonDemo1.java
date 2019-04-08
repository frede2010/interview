package com.lajigaga.Singleton;

/**
 * Created by lajigaga on 2019/4/8 0008.
 * 此单例demo为加同步锁，双重判断
 */
public class SingletonDemo1 {
    //静态实例（私有静态）
    private static SingletonDemo1 instance = null;

    //无参构造
    private SingletonDemo1(){

    }

    //getInstance静态方法
    public static SingletonDemo1 getInstance(){
        if(instance == null){
            synchronized (SingletonDemo1.class){
                if(instance == null){
                    instance = new SingletonDemo1();
                }
            }
        }
        return instance;
    }

}
