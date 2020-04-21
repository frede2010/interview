package com.furui.leetCode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
		 int rev = 0;
		 while (x != 0) {
			 int pop = x % 10;
			 x /= 10;
			 if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
			 if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
			 rev = rev * 10 + pop;
		 }
		 return rev;
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

	/**
	 * 最大利润买卖点
	 * @param prices
	 * @return
	 */
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


	/**
	 * 找出数组中第二大的数
	 * @param nums
	 * @return
	 */
	public int getSec(int[] nums) {
		int first = Integer.MIN_VALUE, second = first;
		for(int i = 0, len = nums.length; i < len; i++) {
			if(nums[i] > second) {
				if(nums[i] > first) {
					second = first;
					first = nums[i];
				} else {
					second = nums[i];
				}
			}
		}
		return second;
	}
	/**
	 * Definition for singly-linked list.
	 * 合并两个有序链表为一个
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	public ListNode mergeTwoListsHe(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		while(l1 != null && l2 != null){
			if(l1.val < l2.val){
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}
		if(l1 == null){
			curr.next = l2;
		}
		if(l2 == null){
			curr.next = l1;
		}
		return dummyHead.next;
	}
	/**
	 * 反转字符串，堆栈方式
	 */
	public static String reverseString(String str){
		if(str == null || str.equals("")){
			return str;
		}
		Stack<Character> characterStack = new Stack<>();
		char[] charArray = str.toCharArray();
		for(char strSingle : charArray){
			characterStack.push(strSingle);
		}
		int k = 0;
		while(!characterStack.empty()){
			charArray[k++] = characterStack.pop();
		}
		return String.copyValueOf(charArray);
	}

	/**
	 * 反转单词
	 * 双端队列方式
	 */
	public static String reverceWord(String s){
		int left = 0, right = s.length() - 1;
		//去掉开头的空白字符
		while(left <= right && s.charAt(left) == ' ') ++left;
		//去掉结尾的空白字符
		while(left <= right && s.charAt(right) == ' ') --right;

		Deque<String> d = new ArrayDeque<>();
		StringBuilder word = new StringBuilder();

		while(left <= right){
			char c = s.charAt(left);
			if((word.length()) != 0 && (c == ' ')){
				// 将单词 push 到队列的头部
				d.offerFirst(word.toString());
				word.setLength(0);
			} else if(c!= ' '){
				word.append(c);
			}
			++left;
		}
		d.offerFirst(word.toString());
		return String.join(" ", d);
	}

	/**
	 * 反转链表
	 * @param head
	 * @return
	 */
	public ListNode reverseListNode(ListNode head){
		ListNode prev = null;
		ListNode curr = head;
		while(curr != null){
			ListNode tempNode = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tempNode;
		}
		return prev;
	}
	/**
	 * 删除链表中倒数第K哥节点
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = dummy;
		ListNode second = dummy;
		// Advances first pointer so that the gap between first and second is n nodes apart
		for (int i = 1; i <= n + 1; i++) {
			first = first.next;
		}
		// Move first to the end, maintaining the gap
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return dummy.next;
	}

	/**
	 * 删除链表中的节点
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	/**
	 * 返回倒数第 k 个节点
	 */
	public int kthToLast(ListNode head, int k) {
		ListNode target=head;
		while(head!=null){
			k--;
			if(k<0){
				k=0;
				target=target.next;
			}
			head=head.next;
		}
		return target.val;
	}

	/**
	 *
	 * @param s
	 * 判断字符串是否合法
	 * @return
	 */
	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<>();
		Map<Character,Character> map = new HashMap<>();
		char[] chars = s.toCharArray();
		map.put(')','(');
		map.put('}','{');
		map.put(']','[');
		for(int i=0;i < s.length();i++){
			if(!map.containsKey(chars[i])) {
				//为左括号时直接入栈
				stack.push(chars[i]);
			}else{
				//为右括号时，如果栈为空或者栈顶与该括号类型不匹配返回false
				if(stack.empty() || map.get(chars[i]) != stack.pop()){
					return false;
				}
			}
		}
		//字符串遍历完毕后，如果栈为空返回true，反之返回false
		return stack.empty();
	}
}
