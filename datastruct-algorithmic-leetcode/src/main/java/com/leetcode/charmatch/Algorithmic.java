package com.leetcode.charmatch;

public class Algorithmic {
    /**
     * 模式匹配支持.*
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        Boolean[][] mem = new Boolean[s.length()][p.length()];
        return dp(s, p, mem);
    }

    private boolean dp(String s, String p, Boolean[][] mem) {
        int sLen = s.length() - 1;
        int pLen = p.length() - 1;
        if (s.isEmpty()) {
            boolean result = p.isEmpty() || (p.length() >= 2 && p.charAt(1) == '*' && dp(s, p.substring(2), mem));
            return result;
        } else if (p.isEmpty()) {
            return false;
        } else {
            if (null != mem[sLen][pLen]) {
                return mem[sLen][pLen];
            }
            boolean first_match = p.charAt(0) == '.' || p.charAt(0) == s.charAt(0);
            if (p.length() >= 2 && p.charAt(1) == '*') {
                boolean result = first_match ?
                        dp(s.substring(1), p, mem) || dp(s.substring(1), p.substring(2), mem) || dp(s, p.substring(2), mem)
                        : dp(s, p.substring(2), mem);
                mem[sLen][pLen] = result;
                return result;
            } else {
                boolean result = first_match && dp(s.substring(1), p.substring(1), mem);
                mem[sLen][pLen] = result;
                return result;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Algorithmic().isMatch("a", "a*a"));
    }
}
