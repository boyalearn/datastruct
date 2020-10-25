package com.xdata.algorithmic.leetcode;

public class Two {
    public static void main(String[] args) {
        int[][] data = rang(createData(9));

        for (int[] item : data) {
            printInt(item);
        }
        System.out.println(data.length);
    }

    private static int[] createData(int n) {
        int[] data = new int[n];
        for (int i = 1; i <= n; i++) {
            data[i - 1] = i;
        }
        return data;
    }

    private static int[][] rang(int[] arr) {
        int[][] result = new int[][]{};
        if (arr.length == 1) {
            return new int[][]{arr};
        } else if (arr.length > 2) {

            for (int v = 0; v < arr.length ; v++) {
                int[][] curArr = rang(arr[v], remove(arr, v));
                result = addArray(result, curArr);
            }
        } else {
            return rang(arr[0], remove(arr, 0));
        }

        return result;

    }

    private static int[][] addArray(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length + arr2.length][];
        int item = 0;
        for (int[] arr : arr1) {
            result[item] = arr;
            item++;
        }
        for (int[] arr : arr2) {
            result[item] = arr;
            item++;
        }
        return result;
    }

    private static int[][] rang(int a, int[] arr) {
        if (arr.length == 1) {
            return new int[][]{{arr[0], a}, {a, arr[0]}};
        } else if(arr.length >2) {
            int[][] result = new int[][]{};

            for (int v = 0; v < arr.length; v++) {
                result = addArray(result, rang(arr[v], remove(arr, v)));
            }
            return result;

        }else{
            int[][] result = new int[2][];
            int[][] data=rang(arr[0], remove(arr, 0));

            for(int k=0;k<2;k++){
                result[k]=add(data[k],a);
            }
            return result;
        }
    }


    private static int[] add(int[] arr, int a) {
        int[] result = new int[arr.length + 1];
        result[0] = a;
        for (int k = 0; k < arr.length; k++) {
            result[k + 1] = arr[k];
        }
        return result;
    }

    private static int[] remove(int[] arr, int i) {
        int[] result = new int[arr.length - 1];

        for (int k = 0; k < arr.length; k++) {
            if (k < i) {
                result[k] = arr[k];
            }
            if (k > i) {
                result[k - 1] = arr[k];
            }
        }
        return result;
    }

    private static void printInt(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
        }
        System.out.println();
    }
}
