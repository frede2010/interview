package com.lajigaga.Proxy;

/**
 * Created by Administrator on 2019/4/1 0001.
 */
public class ProxyAopTest {

    public static void main(String[] args) {
        AddPersonService addPersonServiceProxy = (AddPersonService)new AddPersonAopProxy().AddPersonAopProxy(new AddPersonServiceImpl(), new LoggerAopImpl());
        addPersonServiceProxy.addPerson();
    }
}
