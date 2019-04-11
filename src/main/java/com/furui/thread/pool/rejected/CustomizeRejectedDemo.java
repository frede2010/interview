package com.furui.thread.pool.rejected;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略
 * @author furui
 * @date 2019/4/11 0011
 */
public class CustomizeRejectedDemo {

    private static final int THREADS_SIZE = 2;
    private static final int CAPACITY = 1;

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        /**
         * new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //打印丢弃的任务
            System.out.println(r.toString() + " is discard");
            }
         */
        ExecutorService executorService = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(CAPACITY),  namedThreadFactory, (r, executor) -> {
            //打印丢弃的任务
            System.out.println(r.toString() + " is discard");
            try {
                // 被拒绝的任务能够阻塞执行，从而阻止任务的生产速度
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 10; i++) {
            // 如果直接使用 submit进行提交的话，如果线程中运行的有错误的话，就不会显示出来堆栈信息
            executorService.submit(() -> {
                System.out.println(System.currentTimeMillis()
                        + ":Thread ID:" + Thread.currentThread().getId()
                        + ":Thread Name:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
