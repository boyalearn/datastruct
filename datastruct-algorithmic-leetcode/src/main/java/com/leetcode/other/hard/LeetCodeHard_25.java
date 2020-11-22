package com.leetcode.other.hard;

import java.util.LinkedList;

public class LeetCodeHard_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = null;
        if (!canReverse(head, k)) {
            return head;
        }

        if (k == 1) {
            return head;
        }

        ListNode result = null;

        while (canReverse(head, k)) {
            LinkedList<ListNode> linkedList = new LinkedList();
            ListNode curr = head;
            for (int i = 0; i < k; i++) {
                linkedList.add(i, curr);
                curr = curr.next;
            }

            head = linkedList.getLast().next;

            linkedList.getFirst().next = linkedList.getLast().next;

            if (pre != null) {
                pre.next = linkedList.getLast();
                pre = linkedList.getFirst();
            } else {
                pre = linkedList.getFirst();
            }

            for (int i = linkedList.size() - 1; i > 0; i--) {
                linkedList.get(i).next = linkedList.get(i - 1);
            }

            if (result == null) {
                result = linkedList.getLast();
            }
        }
        return result;
    }

    private boolean canReverse(ListNode head, int k) {
        for (int i = 0; i < k; i++) {
            if (head == null) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode = new LeetCodeHard_25().reverseKGroup(listNode1, 2);
        System.out.println(listNode);


    }
}
