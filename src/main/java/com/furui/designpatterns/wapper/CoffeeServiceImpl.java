package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title CoffeeService
 * @Descripion 装饰者模式----实现咖啡接口中的方法.
 * @date 2019/3/21 20:15
 */
public class CoffeeServiceImpl implements  CoffeeService{
    public void coffee(){
        System.out.println("黑咖啡");
    }
}
