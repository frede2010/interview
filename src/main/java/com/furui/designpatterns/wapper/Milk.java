package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title Milk
 * @Descripion 加奶咖啡
 * @date 2019/3/21 20:23
 */
public class Milk extends CoffeeWapper{
    public Milk(CoffeeService coffeeService){
        super(coffeeService);
    }
    public void coffee(){
        super.coffee();
        System.out.println("加奶");
    }
}
