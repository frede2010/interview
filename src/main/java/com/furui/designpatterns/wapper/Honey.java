package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title Honey
 * @Descripion 加蜂蜜咖啡
 * @date 2019/3/21 20:23
 */
public class Honey extends CoffeeWapper{
    public Honey(CoffeeService coffeeService){
        super((coffeeService));
    }
    public void coffee(){
        super.coffee();
        System.out.println("加蜂蜜");
    }
}
