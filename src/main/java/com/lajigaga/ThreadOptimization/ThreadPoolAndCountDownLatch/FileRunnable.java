package com.lajigaga.ThreadOptimization.ThreadPoolAndCountDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lajigaga on 2019/3/18 0018.
 */
public class FileRunnable<T> implements Runnable{

    private CountDownLatch countDownLatch;
    private List<T> list;
    private int i;


    public FileRunnable(CountDownLatch countDownLatch, List<T> list, int i) {
        super();
        this.countDownLatch = countDownLatch;
        this.list = list;
        this.i = i;
    }

    @Override
    public void run() {

        for(T t: list){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }
}
