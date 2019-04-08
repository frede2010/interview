package com.furui.thread.communication.queue;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author furui
 * @date 2019/4/8 0008
 */
public class MyQueue {
    /**
     * 一个集合
     */
    private final LinkedList<Object> list = new LinkedList<>();

    /**
     * 安全的计数器
     */
    private final AtomicInteger count = new AtomicInteger(0);

    /**
     * 指定队列最大最小值
     */
    private final Integer min = 0;
    private final Integer max = 5;

    /**
     * 初始化锁对象
     */
    private final Object lock = new Object();

    public void put(Object o){
        try {
            synchronized (lock) {
                while (count.get() >= max) {
                    System.out.println("队列元素暂满，元素 " + JSONObject.toJSONString(o) + "等待");
                    lock.wait();
                }
                // 加入元素
                list.add(o);
                // 计数器+1
                count.getAndIncrement();
                System.out.println("元素 " + JSONObject.toJSONString(o) + "被添加");
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object get(){
        synchronized (lock) {
            try {
                while (count.get() <= min) {
                    System.out.println("队列无元素，等待");
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 取出元素
            Object result = list.removeFirst();
            // 计数器-1
            count.decrementAndGet();
            System.out.println("元素 " + JSONObject.toJSONString(result) + "被取出");
            lock.notify();
            return result;
        }
    }

    public AtomicInteger getCount() {
        return count;
    }
}
