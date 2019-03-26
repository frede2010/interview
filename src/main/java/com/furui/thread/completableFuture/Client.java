package com.furui.thread.completableFuture;

import com.furui.algorithm.sort.Sort;

import java.util.concurrent.CompletableFuture;

/**
 * @author furui
 * @date 2019/3/26 0026
 */
public class Client {
    private static void printArray(int[] nums){
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    public static void main(String[] args) {
        int[] nums = {2,1,3,6,5,10,12,8,11,9,0};

        CompletableFuture<Boolean> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sort.selectSort(nums);
            return true;
        }).thenApply(s -> {
            if (s.equals(true)){
                printArray(nums);
                return true;
            }
            return false;
        });
        printArray(nums);
        System.out.println();
        futureA.join();
    }
}
