package com.leetcode.other;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeSimple_202 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();

        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = sum(n);
        }
        return true;
    }

    public int sum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCodeSimple_202().sum(19));
    }

}
