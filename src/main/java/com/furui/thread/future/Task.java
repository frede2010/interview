package com.furui.thread.future;

import java.util.concurrent.Callable;

/**
 * @author furui
 * @date 2019/4/18 0018
 */
public class Task implements Callable<Integer> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++) {
            sum += i;
        }
        return sum;
    }
}
