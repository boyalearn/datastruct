package com.xdata.algorithmic.leetcode;

public class One {

    public static void main(String[] args) {
        System.out.println(new One().lengthOfLongestSubstring("aaw"));
    }

    public int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();

        if (chars.length == 1) {
            return 1;
        }

        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (include(chars, i, j)) {
                    if (j - i > max) max = j - i;
                    break;
                } else if (j == chars.length - 1) {
                    if (j - i + 1 > max) max = j - i + 1;
                }
            }
        }
        return max;
    }

    private boolean include(char[] chars, int i, int j) {
        for (int k = i; k < j; k++) {
            if (chars[j] == chars[k]) {
                return true;
            }
        }
        return false;
    }
}
