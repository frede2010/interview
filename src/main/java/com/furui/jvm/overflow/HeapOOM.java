package com.furui.jvm.overflow;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出java.lang.OutOfMemoryError: Java heap space
 * Java堆用于存储对象的实例，只要不断地创建对象，
 * 并且保证GC roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，那么在对象数量到达最大堆的容量限制后就会产生内存溢出异常
 * @author furui
 * @date 2019/4/11 0011
 */
public class HeapOOM {
    public static void main(String[] args) {
        // 持有“大对象”的引用，防止垃圾回收
        List list = new ArrayList();
        while (true){
            int[] tmp = new int[10000000];
            list.add(tmp);
        }
    }
}
