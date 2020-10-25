package com.leetcode.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OtherOne {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        readData(root, list, 0);
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    private void readData(TreeNode root, List<List<Integer>> list, int level) {
        if (null == root) {
            return;
        }
        List temp = null;
        if (list.size() < level + 1) {
            temp = new LinkedList();
            list.add(temp);
        } else {
            temp = list.get(level);
        }
        temp.add(root.val);
        readData(root.left, list, level + 1);
        readData(root.right, list, level + 1);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return getNode(nums, 0, nums.length);
    }

    private TreeNode getNode(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int middle = (start + end) / 2;

        TreeNode node = new TreeNode(nums[middle]);

        node.left = getNode(nums, start, middle);
        node.right = getNode(nums, middle+1, end);
        return node;
    }


    private Integer minMax = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {

        degree(root, 0);
        return this.minMax;
    }

    private void degree(TreeNode root, int level) {
        if (null == root) {
            if (level < this.minMax) {
                this.minMax = level ;
            }
        } else {
            degree(root.left, level + 1);
            degree(root.right, level + 1);
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }
        ListNode slow = head;

        ListNode fast = head;

        while (null != slow && null != fast) {
            if(slow.next==null){
                return false;
            }else{
                slow=slow.next;
            }

            if(fast.next==null || fast.next.next==null){
                return false;
            }
            fast=fast.next.next;
            if(fast==slow){
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[] mums = new int[]{-10, -3, 0, 5, 9};
        TreeNode node=new OtherOne().sortedArrayToBST(mums);
        System.out.println(node);
    }
}
