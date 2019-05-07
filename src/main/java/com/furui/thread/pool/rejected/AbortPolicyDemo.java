package com.furui.thread.pool.rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 丢弃抛异常策略（RejectedExecutionException）
 * @author furui
 * @date 2019/3/11 0011
 */
public class AbortPolicyDemo {
    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY).拒绝策略为"抛出异常"
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(CAPACITY), new ThreadPoolExecutor.AbortPolicy());

        try {

            // 新建10个任务，并将它们添加到线程池中。
            for (int i = 0; i < 10; i++) {
                Runnable myrun = new MyRunnable("task-" + i);
                pool.execute(myrun);
            }
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
            // 关闭线程池
            pool.shutdown();
        }
    }
}
