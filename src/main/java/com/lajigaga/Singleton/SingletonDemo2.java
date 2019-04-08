package com.lajigaga.Singleton;

/**
 * Created by lajigaga on 2019/4/8 0008.
 * 饿汉式
 * 为什么要使用私有的无参构造，为了减少错误的使用和猜测而定的规则，这样new单例对象的时候就会报错
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance = new SingletonDemo2();

    private SingletonDemo2(){

    }

    public static SingletonDemo2 getInstance() {
        return instance;
    }
}
