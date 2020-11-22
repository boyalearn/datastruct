package com.leetcode.other.simple;

public class LeetCodeSimple_26 {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int i = 0;
        while (i < len - 1) {
            if (nums[i] == nums[i + 1]) {
                for (int k = i + 1; k < len; k++) {
                    nums[k - 1] = nums[k];
                }
                len--;
                i--;
            }
            i++;
        }
        return len;
    }
}
