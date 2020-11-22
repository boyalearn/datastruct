package com.leetcode.other;

public class LeetCodeSimple_941 {
    public boolean validMountainArray(int[] A) {
        int maxIndex = 0;

        if (A.length < 3) {
            return false;
        }

        for (int i = 0; i + 1 < A.length; i++) {
            if (A[i] < A[i + 1]) {
                maxIndex = i + 1;
            } else {
                break;
            }
        }
        if (maxIndex == 0) {
            return false;
        }
        for (int i = maxIndex; i + 1 < A.length; i++) {
            if (A[i] > A[i + 1]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
