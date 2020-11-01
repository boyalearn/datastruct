package com.leetcode.other;

public class LeetCode_8 {
    public int myAtoi(String s) {
        int len = s.length();
        if (s.isEmpty()) {
            return 0;
        }
        int start = 1;
        int sym = 0;
        boolean sub = false;
        while (start <= len && (s.charAt(start - 1) == ' ' || s.charAt(start - 1) == '+' || s.charAt(start - 1) == '-')) {
            if (s.charAt(start - 1) == '+' || s.charAt(start - 1) == '-') {
                sym++;
            }
            if (s.charAt(start - 1) == '-') {
                sub = !sub;
                if (s.charAt(start - 1) == '-' && (start < len && s.charAt(start) == ' ')) {
                    return 0;
                }
            }
            if (s.charAt(start - 1) == '+' && (start < len && s.charAt(start) == ' ')) {
                return 0;
            }
            start++;
        }
        if (sym >= 2) {
            return 0;
        }
        int result = 0;
        for (start = start - 1; start < len; start++) {
            if (0 <= s.charAt(start) - '0' && s.charAt(start) - '0' <= 9) {
                if (!sub) {
                    if (result > Integer.MAX_VALUE / 10 || (result >= Integer.MAX_VALUE / 10 && (s.charAt(start) - '0') >= Integer.MAX_VALUE % 10)) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    if (-result < Integer.MIN_VALUE / 10 || (-result <= Integer.MIN_VALUE / 10 && -(s.charAt(start) - '0') <= Integer.MIN_VALUE % 10)) {
                        return Integer.MIN_VALUE;
                    }
                }
                result = result * 10 + (s.charAt(start) - '0');
            } else {
                return sub ? -result : result;
            }

        }
        return sub ? -result : result;
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode_8().myAtoi("-2147483649"));
    }
}
