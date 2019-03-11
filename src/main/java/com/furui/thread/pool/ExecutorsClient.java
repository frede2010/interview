package com.furui.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池
 * @author furui
 * @date 2019/3/11 0011
 */
public class ExecutorsClient {


    public static void main(String[] args) {
        /**
         * 创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名
         */
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("threadPool-%d").build();

        //Common Thread Pool
        LinkedBlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.SECONDS,
                queue, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i=1;i<=16;i++) {
                pool.execute(new ThreadPoolTest());
        //            System.out.println("线程池中活跃的线程数： " + pool.getPoolSize());
        //            if (queue.size() > 0)
        //            {
        //                System.out.println("----------------队列中阻塞的线程数" + queue.size());
        //            }
            }
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
            // 关闭线程池
            pool.shutdown();
        }

        pool.shutdown();//gracefully shutdown


    }

    static class ThreadPoolTest implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(600);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
