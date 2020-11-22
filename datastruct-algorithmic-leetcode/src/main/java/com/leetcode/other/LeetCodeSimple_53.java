package com.leetcode.other;

public class LeetCodeSimple_53 {

    public int maxSubArray_one(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (max <= sum(nums, i, j)) {
                    max = sum(nums, i, j);
                }
            }
        }
        return max;
    }

    private int sum(int[] nums, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }

    public int maxSubArray_two(int[] nums) {
        int preSum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (preSum <= 0) {
                preSum = nums[i];
            } else {
                preSum = preSum + nums[i];;
            }
            if (preSum > maxSum) {
                maxSum = preSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new LeetCodeSimple_53().maxSubArray_two(nums));
    }
}
