package com.datastruct.algorithmic;


import java.util.LinkedList;
import java.util.List;

/**
 * 分治算法
 *
 * divideConquer(P){
 *     if P达到阈值 {
 *         return 阈值处理结果
 *     }
 *     子问题结果集
 *     for(子问题 ： P){
 *         子问题结果集.add( divideConquer(子问题) );
 *     }
 *
 *     merge(子问题结果集);
 * }
 *
 */
public class DivideAdmin {

    /**
     * 分治算法解决快排
     *
     * @param data
     * @return
     */
    public int[] divideConquer(int[] data) {

        //最小子问题的处理方式
        if (data.length <= 2) {
            if (data.length == 2) {
                if (data[0] > data[1]) {
                    int tmp = data[0];
                    data[0] = data[1];
                    data[1] = tmp;
                }
            }
            return data;
        }
        //任务分解
        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();
        for (int i = 1; i < data.length; i++) {
            if (data[i] > data[0]) {
                right.add(data[i]);
            } else {
                left.add(data[i]);
            }
        }

        //合并子任务结果
        return merge(divideConquer(toArray(left)), data[0], divideConquer(toArray(right)));
    }

    private int[] toArray(List<Integer> item) {
        int[] result = new int[item.size()];
        for (int i = 0; i < item.size(); i++) {
            result[i] = item.get(i);
        }
        return result;
    }

    private int[] merge(int[] item1, int item, int[] item2) {
        int[] data = new int[item1.length + item2.length + 1];
        int i = 0;
        for (Integer num : item1) {
            data[i] = num;
            i++;
        }
        data[i] = item;
        i++;
        for (Integer num : item2) {
            data[i] = num;
            i++;
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = new int[]{3, 2, 1, 4};
        int[] ints = new DivideAdmin().divideConquer(data);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
