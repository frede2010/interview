package com.furui.thread.pool.rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 直接丢弃策略<br/>
 * 线程池能同时运行的任务数量最大为1
 * 线程池的阻塞队列只能有一个线程池阻塞等待。
 * 线程池中共运行了2个任务。第1个任务直接放到Worker中，通过线程去执行；第2个任务放到阻塞队列中等待。其他的任务都被丢弃了
 * @author furui
 * @date 2019/3/11 0011
 */
public class DiscardPolicyDemo {
    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY),线程池的拒绝策略为"丢弃"
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(CAPACITY), new ThreadPoolExecutor.DiscardPolicy());

        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            pool.execute(new MyRunnable("task-"+i));
        }
        // 关闭线程池
        pool.shutdown();
    }
}
