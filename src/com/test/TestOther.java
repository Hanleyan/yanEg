package com.test;

import java.util.List;
import java.util.Map;

public class TestOther {
	
	/**
	 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	 *	注意：答案中不可以包含重复的三元组。
	 *	例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	 *	满足要求的三元组集合为：
	 *	[
	 *	  [-1, 0, 1],
	 *	  [-1, -1, 2]
	 *	]
	 */
	@SuppressWarnings("unused")
	public static List<List<Integer>> threeSum(int[] nums) {
		int a,b,c;
		for (int i = 0; i < nums.length; i++) {
			a = nums[i];
			for (int j = 1; j == i + 1; j++) {
				
			}
			
		}
		
		return null;
        
    } 

	public static void main(String[] args) {
		int[] nums = {-9,3,6,1};
		List<List<Integer>> list = threeSum(nums);
		System.out.println(list);
		
		
		Map<String, Object> map = ReturnJsonUtil.returnSuccessWithMsg("好的");
		System.out.print(map);
	}
}
