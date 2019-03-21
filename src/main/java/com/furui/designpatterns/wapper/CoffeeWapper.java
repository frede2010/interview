package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title CoffeeWapper
 * @Descripion 在给黑咖啡添加调味品之前,我们先定义一个类,这个类是所有添加调味品咖啡的父类,进行包装
 * @date 2019/3/21 20:19
 */
public class CoffeeWapper implements CoffeeService{
    private CoffeeService coffeeService;
    public CoffeeWapper(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }

    public void coffee(){
        coffeeService.coffee();
    }
}
