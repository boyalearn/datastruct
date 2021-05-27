package com.datastruct.algorithmic;

/**
 *
 */
public class DynamicPlan {
    int dynamicPlan(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return dynamicPlan(n - 1) + dynamicPlan(n - 2);
        }
    }

    int steps(int n) {
        if (n <= 4) {
            return n;
        } else {
            return steps(n - 1) + steps(n - 2) + steps(n - 3);
        }
    }

    int say(int curr, int result) {
        int selected = 2;
        if (result <= 2) {
            return 2;
        } else {
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new DynamicPlan().steps(5));
    }
}
