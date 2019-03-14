package com.furui.datatype.string;

/**
 * @author furui
 * @date 2019/3/14 0014
 */
public class HowManyObject {

    /**
     * 考虑类加载阶段和实际执行时。
     * 类加载对一个类只会进行一次。”xyz”在类加载时就已经创建并驻留了（如果该类被加载之前已经有”xyz”字符串被驻留过则不需要重复创建用于驻留的”xyz”实例）。驻留的字符串是放在全局共享的字符串常量池中的。
     * 在这段代码后续被运行的时候，”xyz”字面量对应的String实例已经固定了，不会再被重复创建。所以这段代码将常量池中的对象复制一份放到heap中，并且把heap中的这个对象的引用交给s1 持有。
     * 这条语句创建了2个对象。
     * @param args
     */
    public static void main(String[] args) {
        //创建了几个对象？
        String s1 = new String("xyz");
    }

}
