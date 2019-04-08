package com.furui.thread.communication.queue;

/**
 * @author furui
 * @date 2019/4/8 0008
 */
public class Client {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        intQueue(queue);
        new Thread(()-> {
            queue.put("f");
            queue.put("g");
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            try {
                Thread.sleep(1000);
                queue.get();
                Thread.sleep(2000);
                queue.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

    static void intQueue(MyQueue queue){
        queue.put("a");
        queue.put("b");
        queue.put("c");
        queue.put("d");
        queue.put("e");
        System.out.println("当前元素个数：" + queue.getCount().get());
    }
}
