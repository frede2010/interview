package com.wangheng.grammer;

/**
 * @author wangheng
 * time 20190623
 * 字符串语法
 */
public class TestString {

    public static void main(String[] args) {

        String str1 = "22";
        String str2 = "world";
        String str3 = "22"+str2;
        String str4 = "22world";
        String str5 = str1+str2;
        String str6 = str1+5;
        System.out.println(str3==str4);
        System.out.println(str4==str5);
        System.out.println(str6);
    }

}
