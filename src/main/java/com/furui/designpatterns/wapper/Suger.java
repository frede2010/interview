package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title Suger
 * @Descripion 加糖咖啡
 * @date 2019/3/21 20:23
 */
public class Suger extends CoffeeWapper{
    public Suger(CoffeeService coffeeService){
        super(coffeeService);
    }
    public void coffee(){
        super.coffee();
        System.out.println("加糖");
    }
}
