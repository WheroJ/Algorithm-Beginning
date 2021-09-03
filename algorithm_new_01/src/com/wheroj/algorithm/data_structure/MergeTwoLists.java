package com.wheroj.algorithm.data_structure;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode head = l1.val <= l2.val ? l1 : l2;

        ListNode cur1 = head.next;
        ListNode cur2 = head == l1 ? l2 : l1;

        ListNode pre = head;
        while (cur2 != null && cur1 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
//                pre = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
//                pre = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }

        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    private static void print(ListNode header) {
        while (header != null) {
            System.out.print(header.val + " ");
            header = header.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 1;
        head.next = new ListNode();
        head.next.val = 3;
        head.next.next = new ListNode();
        head.next.next.val = 5;
        head.next.next.next = new ListNode();
        head.next.next.next.val = 7;

        ListNode head2 = new ListNode();
        head2.val = 2;
        head2.next = new ListNode();
        head2.next.val = 4;
        head2.next.next = new ListNode();
        head2.next.next.val = 6;
        head2.next.next.next = new ListNode();
        head2.next.next.next.val = 8;
        head2.next.next.next.next = new ListNode();
        head2.next.next.next.next.val = 9;
        head2.next.next.next.next.next = new ListNode();
        head2.next.next.next.next.next.val = 12;

        print(mergeTwoLists(head, head2));
    }
}
