package com.furui.jvm.overflow;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * java.lang.OutOfMemoryError: Metaspace
 * 因为在调用CGLib的创建代理时会生成动态代理类，即Class对象到Metaspace
 * @author furui
 * @date 2019/4/11 0011
 */
public class MethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            new OOMObject();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            // 关闭CGLib缓存，否则总是生成同一个类
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            //无限创建动态代理，生成Class对象
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
