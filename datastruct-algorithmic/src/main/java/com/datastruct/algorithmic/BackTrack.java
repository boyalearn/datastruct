package com.datastruct.algorithmic;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 * result = []
 * void backtrack(路径, 选择列表){
 *     if 满足结束条件 {
 *         result.add(路径)
 *         return
 *     }
 *     for 选择 in 选择列表 {
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 *     }
 * }
 *
 */
public class BackTrack {

    /**
     * 回溯算法
     *
     * @param result       结果集
     * @param selectedList 选择列表
     * @param item         当前选择项
     */
    public void backTrack(List<LinkedList<String>> result, LinkedList<String> selectedList, LinkedList<String> item) {
        // 判断一个结果集是否结束
        if (item.size() == selectedList.size()) {
            LinkedList<String> linkedList = new LinkedList<>();
            for (String str : item) {
                linkedList.add(str);
            }
            result.add(linkedList);
            return;
        }
        for (String str : selectedList) {
            //跳过当前已经选择的对象
            if (item.contains(str)) {
                continue;
            }
            // 选择一条路径
            item.addLast(str);
            backTrack(result, selectedList, item);
            // 撤销一条路径
            item.removeLast();
        }

    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        List<LinkedList<String>> result = new LinkedList<>();

        LinkedList<String> selectedList = new LinkedList<String>();
        selectedList.add("1");
        selectedList.add("2");
        selectedList.add("3");
        new BackTrack().backTrack(result, selectedList, linkedList);

        for (LinkedList item : result) {
            System.out.println(item);
        }
    }
}
