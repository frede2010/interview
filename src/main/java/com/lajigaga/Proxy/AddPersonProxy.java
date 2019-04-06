package com.lajigaga.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/29 0029.
 */
public class AddPersonProxy implements InvocationHandler {

    private Object target;

    public AddPersonProxy(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理开始");
        method.invoke(target,args);
        System.out.println("执行结束");
        return null;
    }
}
