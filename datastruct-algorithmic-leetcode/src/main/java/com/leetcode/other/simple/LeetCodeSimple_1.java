package com.leetcode.other.simple;

public class LeetCodeSimple_1 {


    public int hammingDistance(int x, int y) {
        int sum = 0;
        int bit = x ^ y;
        String s = Integer.toBinaryString(bit);
        for(char c :s.toCharArray()){
            if(c == '1'){
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCodeSimple_1().hammingDistance(1, 4));

    }
}
