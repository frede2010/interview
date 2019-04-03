package com.furui.datatype.string;

/**
 * @author furui
 * @date 2019/3/14 0014
 */
public class Intern {
    /**
     * String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
     * new String("str")会在编译期间存进常量池中一份对象，在运行期间存进堆中一份对象
     * new String("str") + new String("str") 会在编译期间存进常量池1份str对象，运行期间存进3分对象，包括2份str和相加后的值
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
        // true
        System.out.println("s1 == s3? " + (s1 == s3));

        // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s4指向的堆中的对象（注此时常量池不会生成"11"）
        String s4 = new String("1") + new String("1");
        // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        s4.intern();
        // jdk1.7之后，常量池中的地址其实就是s4的地址
        String s5 = "11";
        // jdk1.7之前false， jdk1.7之后true
        System.out.println(s4 == s5);
    }

}
