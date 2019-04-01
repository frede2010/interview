package com.lajigaga.Proxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/1 0001.
 */
public interface LoggerAop {
    void start(Method method);
    void end(Method method);
}
