package com.furui.datatype.string;

/**
 * @author furui
 * @date 2019/3/14 0014
 */
public class Intern {
    /**
     * String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
     * @param args
     */
    public static void main(String[] args) {
        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        // false
        System.out.println("s1 == s2? " + (s1 == s2));
        // true
        System.out.println("s3 == s2? " + (s3 == s2));
    }

}
