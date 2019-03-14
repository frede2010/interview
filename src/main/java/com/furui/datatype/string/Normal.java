package com.furui.datatype.string;

/**
 * @author furui
 * @date 2019/3/14 0014
 */
public class Normal {
    /**
     * "abcd"是在常量池中拿对象，new String("abcd")是直接在堆内存空间创建一个新的对象。只要使用new方法，便需要创建新的对象。
     * 连接表达式 +，只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入常量池中。
     * 对于字符串变量的“+”连接表达式，它所产生的新对象都不会被加入字符串池中，其属于在运行时创建的字符串，具有独立的内存地址,所以不引用自同一String对象。
     * @param args
     */
    public static void main(String[] args) {
        String str01 = "abcd";
        String str02 = new String("abcd");
        //false
        System.out.println(str01==str02);

        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        // true
        System.out.println("string" == "str" + "ing");
        //false
        System.out.println(str3 == str4);

        String str5 = "string";
        //true
        System.out.println(str3 == str5);
    }

}
