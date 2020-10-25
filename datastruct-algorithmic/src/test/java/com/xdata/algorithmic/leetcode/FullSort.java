package com.xdata.algorithmic.leetcode;

import java.util.LinkedList;
import java.util.List;

public class FullSort {
    public static void main(String[] args) {
        FullSort fun = new FullSort();
        LinkedList<Integer> item = new LinkedList();
        fun.fullSort(new int[]{1, 2, 3, 4, 5, 6}, item);
        for (List<Integer> data : fun.result) {
            System.out.println(data);
        }
    }

    List<List<Integer>> result = new LinkedList<>();

    private List<List<Integer>> fullSort(int[] nums, LinkedList item) {

        if (nums.length == item.size()) {
            result.add(new LinkedList<>(item));
        }

        for (int i = 0; i < nums.length; i++) {
            if (item.contains(nums[i])) {
                continue;
            }
            item.add(nums[i]);
            fullSort(nums, item);
            item.removeLast();
        }
        return result;
    }
}
