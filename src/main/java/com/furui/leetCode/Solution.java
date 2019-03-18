package com.furui.leetCode;

import java.util.*;

public class Solution {
	
	/**
	 * 1. Two Sum <br/>
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 * You may assume that each input would have exactly one solution, and you may not use the same element twice.<br/>
	 * 经测试，时间上map话费的更多，待验证
	 * 双重for循环，时间复杂度O(n^2)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumFor(int[] nums, int target) {
		int[] sumArray = new int[2];
        for(int i = 0; i < nums.length; i++){
        	for(int j = i + 1; j < nums.length; j++){
        		if(nums[i] + nums[j] == target){
        			sumArray[0] = i;
        			sumArray[1] = j;
        			break;
        		}
        	}
        }
        return sumArray;
    }
	
	/**
	 * 利用Map,时间复杂度O(n)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumMap(int[] nums, int target) {
		int[] sumArray = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++){
			if(map.containsKey(target - nums[i])){
				sumArray[0] = map.get(target - nums[i]);
				sumArray[1] = i;
				break;
			}else{
				map.put(nums[i], i);
			}
		}
        return sumArray;
    }	
	/**
	 * 2.Reverse Integer<br/>
	 * Reverse digits of an integer<br/>
	 * The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows. 
	 */
	 public int reverse(int x) {
		int reverseNum = 0;
        while(x != 0 && x % 10 == 0){
        	int result = reverseNum * 10 + x % 10;
        	if ((result - x % 10) / 10 != reverseNum)
        		return 0;
        	x = x / 10;
        	reverseNum  = result;
        }
        return reverseNum;
	 }
	 
	/**
	 * 3.Palindrome Number<br/>
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int reverseNum = 0;
        int o = x;
        while(x != 0){
            int num = reverseNum * 10 + x % 10;
            if((num - x % 10)/10 != reverseNum) return false;
            x = x / 10;
            reverseNum = num;
        }
        if(reverseNum == o) return true;
        else return false;
    }
	
	/**
	 * 4.Roman to Integer <br/>
	 * Given a roman numeral, convert it to an integer.
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		return 0;
    }
	
	/**
	 * 5.Longest Common Prefix <br/>
	 * Write a function to find the longest common prefix string amongst an array of strings. 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		String commonStr = strs.length == 0 ? "" : strs[0];
		if(strs.length < 2) return commonStr;
        for(int i = 1; i < strs.length; i++){
        	 int loop = commonStr.length() < strs[i].length() ? commonStr.length() : strs[i].length();
        	 int j = 0;
        	 for(j = 0; j < loop; j++){
        		 if(commonStr.charAt(j) != strs[i].charAt(j)){
        			 break;
        		 }
        	 }
        	 commonStr = commonStr.substring(0, j);
        }
        return commonStr;
    }
	
	/**
	 * 5.Longest Common Prefix <br/>
	 * Write a function to find the longest common prefix string amongst an array of strings. 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix2(String[] strs) {
		Arrays.sort(strs);
		String firstStr = strs.length == 0 ? "" : strs[0];
		String lastStr = strs.length == 0 ? "" : strs[strs.length - 1];
		int loop = firstStr.length() <lastStr.length() ? firstStr.length() : lastStr.length();
		int j = 0;
	   	for(j = 0; j < loop; j++){
	   		if(firstStr.charAt(j) != lastStr.charAt(j)){
	   			break;
	   		}
	   	}
        return firstStr.substring(0, j);
    }
	
	/**
	 * 5.Longest Common Prefix <br/>
	 * Write a function to find the longest common prefix string amongst an array of strings. 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix3(String[] strs) {
		if(strs == null || strs.length == 0) return "";
		String commonStr = strs[0];
		int commonLenth = commonStr.length();
		for(String str : strs){
			while(!str.startsWith(commonStr)){
				commonStr = commonStr.substring(0, --commonLenth);
				if(commonLenth == 0) return "";
			}
		}
		return commonStr;
    }
	
	/**
	 * 6. Valid Parentheses <br/>
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
	 * determine if the input string is valid.The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		if(s == null || s == "") {
			return false;
		}
		Stack<Character> stack = new Stack<>();
		char[] ch = s.toCharArray();
		for(char c : ch){
			if(c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if(c == ')' && !stack.isEmpty() && stack.pop() == '(') {
				continue;
			}else if(c == '}' && !stack.isEmpty() && stack.pop() == '{')  {
				continue;
			}else if(c == ']' && !stack.isEmpty() && stack.pop() == '[')  {
				continue;
			}else{
				return false;
			}
		}
		return stack.isEmpty();
    }
	
	/**
	 * 7.
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode tail = dummyHead;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail = tail.next;
		}

		if (l1 != null) {
			tail.next = l1;
		}
		if (l2 != null) {
			tail.next = l2;
		}

		return dummyHead.next;
    }
	
	/**
	 * 8.3Sum
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums == null || nums.length < 3) return result;
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 2; i++){
			if(i == 0 || ( i > 0 && nums[i] != nums[i-1])){
				int reverse = - nums[i];
				int low = i+1;
				int high = nums.length - 1;
				while(low < high){
					if(nums[low] + nums[high] == reverse){
						result.add(Arrays.asList(nums[i], nums[low], nums[high]));
						while (low < high && nums[low] == nums[low+1]) {
							low++;// skip same result
						}
	                    while (low < high && nums[high] == nums[high-1]) {
							high--;// skip same result
						}
	                    low++; high--;
					}else if(nums[low] + nums[high] < reverse){
						low++;
					}else{
						high--;
					}
				}
			}
		}
		return result;
    }
	
	 public int maxProfit(int[] prices) {
		 int length = prices.length;
		 int max = 0;
		 for(int i = length - 1; i>=1; i--){
			 int index = prices[i];
			 int cursor = i-1;
			 while(cursor >= 0){
				 if(max < index - prices[cursor]){
					 max = index - prices[cursor];
				 }
				 cursor--;
			 }
		 }
		 return max;
	 }
}
