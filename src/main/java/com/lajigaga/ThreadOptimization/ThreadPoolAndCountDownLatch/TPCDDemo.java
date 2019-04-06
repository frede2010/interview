package com.lajigaga.ThreadOptimization.ThreadPoolAndCountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lajigaga on 2019/3/18 0018.
 */
public class TPCDDemo {

    private static ExecutorService executorService =Executors.newFixedThreadPool(100);
    private static CountDownLatch countDownLatch = new CountDownLatch(100);
    private static List<FileInfo> fileInfoList = new ArrayList<>();
    private static List<List<FileInfo>> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        addFile();
        superList();
        Long starTime = System.currentTimeMillis();
        int i = 0;
        for(List<FileInfo> fileInfo : list){
            executorService.submit(new FileRunnable(countDownLatch, fileInfo, i));
            i++;
        }
        countDownLatch.await();
        Long endTime = System.currentTimeMillis();
        executorService.shutdown();
        System.out.println(i +"个线程用时" + (endTime - starTime) + "ms");
    }

    private static void addFile(){
        for(int i= 0; i< 3000; i++){
            FileInfo fileInfo = new FileInfo("小哥哥的身份证", "IDCARD", 200L);
            fileInfoList.add(fileInfo);
        }

    }

    private static void superList(){
        for(int i=0; i< 100; i++){
            list.add(fileInfoList);
        }
    }

}
