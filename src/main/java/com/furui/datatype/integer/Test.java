package com.furui.datatype.integer;

/**
 * @author furui
 * @date 2019/3/7 0007
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 整数类型在-128到127之间的时候有个缓存。
         * 当给一个Integer赋予一个int类型的时候会调用Integer的静态方法valueOf()，
         * 这个方法会先判断数值是不是在缓冲区之间，是就直接返回缓冲区对应的引用。否则new Integer
         */
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = 1;
        Integer d = 1;
        int e = 1;
        //false
        System.out.println(a == b);
        //false
        System.out.println(a == c);
        //false
        System.out.println(b == c);
        //true
        System.out.println(c == d);
        //true
        System.out.println(a == e);
        //true
        System.out.println(c == e);
    }
}
