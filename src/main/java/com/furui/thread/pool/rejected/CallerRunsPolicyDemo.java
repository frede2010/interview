package com.furui.thread.pool.rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 直接处理策略<br/>
 * 调用线程处理该任务,当有任务添加到线程池被拒绝时，线程池会将被拒绝的任务添加到线程池正在运行的线程取运行
 * @author furui
 * @date 2019/3/11 0011
 */
public class CallerRunsPolicyDemo {
    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY),拒绝策略为"CallerRunsPolicy"
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            Runnable myrun = new MyRunnable("task-"+i);
            pool.execute(myrun);
        }

        // 关闭线程池
        pool.shutdown();
    }
}
