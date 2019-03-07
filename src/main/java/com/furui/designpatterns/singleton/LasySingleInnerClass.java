package com.furui.designpatterns.singleton;

/**
 * 内部类方式安全单例（性能最好之一）
 * @author furui
 * @date 2019/3/5 0005
 */
public class LasySingleInnerClass {
    private LasySingleInnerClass(){}

    /**
     * 线程安全由ClassLoader来实现，并且由于SingletonHolder是内部类，所以只有在第一次调用getInstance()方法时才会被加载
     */
    private static class SingletonHolder{
        private static final LasySingleInnerClass INSTANCE = new LasySingleInnerClass();
    }

    public static LasySingleInnerClass getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
