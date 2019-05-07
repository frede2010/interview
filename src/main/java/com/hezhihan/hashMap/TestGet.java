package com.hezhihan.hashMap;

import com.hezhihan.hashMap.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title TestGet
 * @Descripion TODO
 * @date 2019/4/22 16:29
 */
public class TestGet {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("何志菡");
        s1.setAge(17);

        Student s2 = new Student();
        s2.setName("何志菡");
        s2.setAge(17);

        Map<Student,Integer> testMap = new HashMap<>();
        testMap.put(s1,100);
//        testMap.put(s2,200);

        Integer sss = testMap.get(s2);
        System.out.println(sss);
        System.out.println(testMap.size());
    }
}
