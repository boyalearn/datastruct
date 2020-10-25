package com.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口技巧
 * <p>
 * string s, t;
 * // 在 s 中寻找 t 的「最小覆盖子串」
 * int left = 0, right = 0;
 * string res = s;
 * <p>
 * while(right < s.size()) {
 * window.add(s[right]);
 * right++;
 * // 如果符合要求，移动 left 缩小窗口
 * while (window 符合要求) {
 * // 如果这个窗口的子串更短，则更新 res
 * res = minLen(res, window);
 * window.remove(s[left]);
 * left++;
 * }
 * }
 * return res;
 */
public class SlidingWindow {





    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        char[] data = s.toCharArray();
        Map<Character, Integer> window = new HashMap<>();

        int longSize = 0;

        while (right < s.length()) {
            char r = data[right];
            window.put(r, window.getOrDefault(r, 0) + 1);

            right++;
            while (window.get(r) > 1) {
                char l = data[left];
                window.put(l, window.get(l) - 1);
                left++;
            }
            longSize = Math.max(longSize, right - left);
        }

        return longSize;

    }


    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int begin = 0;
        int maxLen = 1;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public boolean isPalindrome(int x) {
        String data = String.valueOf(x);
        int left = 0;
        int right = data.length() - 1;
        while (left < right) {
            if (data.charAt(left) != data.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }



    public static void main(String[] args) {
        //System.out.println(new SlidingWindow().lengthOfLongestSubstring("abasdfcsd"));

        System.out.println(new SlidingWindow().isPalindrome(-121));
    }
}
