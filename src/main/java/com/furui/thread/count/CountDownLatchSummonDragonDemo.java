package com.furui.thread.count;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 我们知道的集齐七颗龙珠就可以召唤神龙，那我们就一起召唤一下，下边我需要派7个人（7个线程）去分别去找这7颗不同的龙珠，每个人找到之后回来告诉我还需要等待的龙珠个数减1个，那么当全部的人都找到龙珠之后，那么我就可以召唤神龙了。
 * @author furui
 * @date 2019/4/10 0010
 */
public class CountDownLatchSummonDragonDemo {
    private static final int THREAD_COUNT_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("第" + index + "颗龙珠已收集到！");
                    //模拟收集第i个龙珠,随机模拟不同的寻找时间
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每收集到一颗龙珠,需要等待的颗数减1
                countDownLatch.countDown();
            }).start();
        }
        //等待检查，即上述7个线程执行完毕之后，执行await后边的代码
        countDownLatch.await();
        System.out.println("集齐七颗龙珠！召唤神龙！");
    }
}
