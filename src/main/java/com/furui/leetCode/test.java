package com.furui.leetCode;

import java.util.Date;
import java.util.List;

public class test {

	public static void main(String[] args) {
		Solution solution = new Solution();
//		int[] nums = {3, 2, 4};
//		int target = 6;
//		int[] result = towSum(twoSum, nums, target);
//		printNums(result);
		String str = "(){}{}";
		int[] a = {2, 7, 11, 15};
		solution.twoSumMap(a, 9);
		System.out.println(solution.maxProfit(a));
//		List<List<Integer>> list = solution.threeSum(a);
//		System.out.println(list);
//		System.out.println(solution.isValid(str));
//		int a = ReverseInteger.reverse(-2147447412);
//		System.out.println(a);
	}
	
	
	public static void printNums(Object[] nums){
		for(Object numbers : nums){
			System.out.print(numbers+" ");
		}
		System.out.println();
	}

}
