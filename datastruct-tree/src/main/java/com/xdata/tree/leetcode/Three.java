package com.xdata.tree.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Three {
    public static <ThreeNode> void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        TreeNode two1 = new TreeNode(4);
        TreeNode two2 = new TreeNode(8);
        TreeNode three1 = new TreeNode(11);
        TreeNode three2 = new TreeNode(13);
        TreeNode three3 = new TreeNode(4);
        TreeNode four1 = new TreeNode(7);
        TreeNode four2 = new TreeNode(2);
        TreeNode four3 = new TreeNode(5);
        TreeNode four4 = new TreeNode(1);

        root1.left = two1;
        root1.right = two2;
        two1.left = three1;
        two2.left = three2;
        two2.right = three3;
        three1.left = four1;
        three1.right = four2;
        three3.left = four3;
        three3.right = four4;

        List<List<Integer>> lists = pathSum(root1, 22);
        System.out.println(lists);

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        pushSum(root, sum, null, list);
        return list;
    }

    /**
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     *
     * @param root
     * @param sum
     * @param list
     * @param result
     * @return
     */
    public static List<List<Integer>> pushSum(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        if (null == list) {
            list = new ArrayList<>();
        }
        if (root != null) {
            list.add(root.val);
            pushSum(root.left, sum, list, result);
            if (root.left != null) {
                list.remove(list.size() - 1);
            }
            pushSum(root.right, sum, list, result);
            if (root.right != null) {
                list.remove(list.size() - 1);
            }
        }
        if (root != null && root.left == null && null == root.right) {
            int valSum = 0;
            List<Integer> valList = new ArrayList<>();
            for (int val : list) {
                valSum += val;
                valList.add(val);
            }
            if (sum == valSum) {
                result.add(valList);
            }
        }
        return result;


    }

}
