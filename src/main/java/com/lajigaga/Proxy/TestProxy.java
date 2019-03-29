package com.lajigaga.Proxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/29 0029.
 */
public class TestProxy {

    public static void main(String[] args) {
        AddPersonService addPersonService = new AddPersonServiceImpl();
        AddPersonProxy addPersonProxy = new AddPersonProxy(addPersonService);

        AddPersonService addPersonServiceProxy = (AddPersonService)Proxy.newProxyInstance(addPersonService.getClass().getClassLoader(),
                addPersonService.getClass().getInterfaces(), addPersonProxy);

        addPersonServiceProxy.addPerson();

    }
}
