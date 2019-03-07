package com.furui.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 代理是一种常用的设计模式，其目的就是为其他对象提供一个代理以控制对某个对象的访问
 * @author furui
 * @date 2019/3/5 0005
 */
public class DynamicProxy implements InvocationHandler{

    private Object target;

    /**
     * @param target 实际对象
     * @return
     */
    public Object getInstance(Object target){
        this.target = target;
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 接口的方法
     * @param proxy 表示代理
     * @param method 原对象被调用的方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--- 开始执行："+ method);
        Long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        Long end = System.currentTimeMillis();
        System.out.println("--- " + method + " 执行完成, 耗时 "+ (end - start));
        return result;
    }
}
