package com.wangheng.collection;

import java.util.*;

public class TestCollection {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("1");
//        list.add("");
//        list.add(null);

        Set set = new TreeSet();
        //list去重
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }
        Iterator it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

    }
}