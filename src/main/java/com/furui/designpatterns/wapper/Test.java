package com.furui.designpatterns.wapper;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title Test
 * @Descripion TODO
 * @date 2019/3/21 20:28
 */
public class Test {
    public static void main(String[] args){
        //黑咖啡
        CoffeeService coffeeService = new CoffeeServiceImpl();

        //加糖咖啡
        CoffeeService sugarCoffe = new Suger(coffeeService);
        sugarCoffe.coffee();

        //加糖加奶咖啡
        CoffeeService milkCoffee = new Milk(sugarCoffe);
        milkCoffee.coffee();

        //加糖加奶加蜂蜜咖啡
        CoffeeService honeyCoffee = new Honey(milkCoffee);
        honeyCoffee.coffee();

        //加糖加蜂蜜咖啡
        CoffeeService sugarHoneyCoffee = new Honey(sugarCoffe);
        sugarHoneyCoffee.coffee();
    }
}
