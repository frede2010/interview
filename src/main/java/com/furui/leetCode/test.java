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
//		String str = "(){}{}";
//		int[] a = {2, 7, 11, 15};
//		solution.twoSumMap(a, 9);
//		System.out.println(solution.maxProfit(a));
//		List<List<Integer>> list = solution.threeSum(a);
//		System.out.println(list);
//		System.out.println(solution.isValid(str));
//		int a = ReverseInteger.reverse(-2147447412);
//		System.out.println(a);
		ListNode l1_1 = new ListNode(1);
		ListNode l1_2 = new ListNode(4);
		ListNode l1_3 = new ListNode(5);
		ListNode l1_4 = new ListNode(8);
		l1_1.next = l1_2;
		l1_2.next = l1_3;
		l1_3.next = l1_4;
		ListNode l2_1 = new ListNode(0);
		ListNode l2_2 = new ListNode(2);
		ListNode l2_3 = new ListNode(3);
		ListNode l2_4 = new ListNode(6);
		l2_1.next = l2_2;
		l2_2.next = l2_3;
		l2_3.next = l2_4;
		ListNode l3 = solution.mergeTwoLists(l1_1, l2_1);
	}
	
	
	public static void printNums(Object[] nums){
		for(Object numbers : nums){
			System.out.print(numbers+" ");
		}
		System.out.println();
	}

}
