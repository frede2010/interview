package com.furui.thread.communication.easydemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author furui
 * @date 2019/4/8 0008
 */
public class MyList {
    private List list = new ArrayList();
    public void add(){
        list.add("元素");
    }
    public int size(){
        return list.size();
    }
}
