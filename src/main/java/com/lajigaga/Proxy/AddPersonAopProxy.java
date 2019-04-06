package com.lajigaga.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/4/1 0001.
 */
public class AddPersonAopProxy implements InvocationHandler{

    private Object target;

    private Object aop;

    public Object AddPersonAopProxy(Object target, Object aop){
        this.target = target;
        this.aop = aop;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行日志开始
        //反射得到操作者的实例
        Class<?> aClass = this.aop.getClass();
        Method start = aClass.getDeclaredMethod("start", new Class[]{Method.class});
        start.invoke(this.aop, new Object[]{method});
        method.invoke(this.target, args);
        Method end = aClass.getDeclaredMethod("end", new Class[]{Method.class});
        end.invoke(this.aop, new Object[]{method});
        return null;
    }
}
