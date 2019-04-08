package com.lajigaga.Singleton;

/**
 * Created by lajigaga on 2019/4/8 0008.
 * 静态内部类
 * 达到了lazy loading的效果，即按需创建实例
 */
public class SingletonDemo3 {
    private SingletonDemo3(){

    }

    private static class SingletonHolder{
        private final static SingletonDemo3 instance = new SingletonDemo3();
    }

    public static SingletonDemo3 getInstance(){
        return SingletonHolder.instance;
    }
}
