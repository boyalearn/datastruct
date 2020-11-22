package com.leetcode.other.simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCodeSimple_242 {
    public boolean isAnagram(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            if (table.containsKey(c)) {
                table.put(c, table.get(c) + 1);
            } else {
                table.put(c, 1);
            }
        }

        for (char c : s.toCharArray()) {
            if (!table.containsKey(c)) {
                return false;
            } else {
                if (table.get(c) <= 0) {
                    return false;
                } else {
                    int vale=table.get(c) - 1;
                    if(vale==0){
                        table.remove(c);
                    }else {
                        table.put(c, vale);
                    }
                }
            }
        }
        return table.isEmpty();
    }
}
