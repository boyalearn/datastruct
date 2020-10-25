package com.leetcode.doublepointer;

public class DoublePointer {


    public static void main(String[] args) {
        int[] arr1 = new int[]{1};
        int[] arr2 = new int[]{2, 4};
        System.out.println(new DoublePointer().findMedianSortedArrays(arr1, arr2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int midLength = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 + length2 == 0) {
            return 0;
        }

        midLength = (length1 + length2);

        int index1 = 0;
        int index2 = 0;
        int[] midArr = new int[midLength];
        int index = 0;

        int num1;
        int num2;
        while (index1 < length1 || index2 < length2) {
            if (index1 >= length1) {
                num1 = Integer.MAX_VALUE;
            } else {
                num1 = nums1[index1];
            }
            if (index2 >= length2) {
                num2 = Integer.MAX_VALUE;
            } else {
                num2 = nums2[index2];
            }

            if (num1 < num2) {
                midArr[index] = num1;
                index1++;
            } else {
                midArr[index] = num2;
                index2++;
            }
            index++;
        }

        if ((length1 + length2) % 2 == 1) {
            return midArr[midArr.length / 2];
        } else {
            return (midArr[midArr.length / 2] + midArr[midArr.length / 2 - 1]) / 2.0;
        }

    }


}
