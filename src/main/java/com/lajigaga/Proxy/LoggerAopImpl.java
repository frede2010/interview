package com.lajigaga.Proxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/1 0001.
 */
public class LoggerAopImpl implements LoggerAop{
    @Override
    public void start(Method method) {
        System.out.println("日志记录开始。。。");
    }

    @Override
    public void end(Method method) {
        System.out.println("日志记录结束。。。");
    }
}
