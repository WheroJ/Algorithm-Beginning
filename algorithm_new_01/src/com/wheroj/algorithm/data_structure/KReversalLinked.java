package com.wheroj.algorithm.data_structure;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class KReversalLinked {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode getK(ListNode start, int k) {
        if (k == 0 || start == null) {
            return start;
        }

        while ((--k) > 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    private static ListNode reversal(ListNode start, ListNode end) {
        if (start == null) {
            return null;
        }

        if (start == end) {
            return start;
        }

        ListNode pre = end.next;
        ListNode next;
        ListNode cur = start;
        while (cur != null && pre != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode f(ListNode head, int k) {
        ListNode lastEnd = head;
        ListNode stageEnd = getK(head, k);
        if (stageEnd == null) {
            return head;
        }
        ListNode nextStart = stageEnd.next;
        ListNode newHead = reversal(head, stageEnd);

        while (stageEnd != null) {
            head = nextStart;
            stageEnd = getK(head, k);
            if (stageEnd == null) {
                return newHead;
            }

            nextStart = stageEnd.next;
            lastEnd.next = reversal(head, stageEnd);
            lastEnd = head;
        }
        return newHead;
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
        head.next.val = 2;
        head.next.next = new ListNode();
        head.next.next.val = 3;
        head.next.next.next = new ListNode();
        head.next.next.next.val = 4;
        head.next.next.next.next = new ListNode();
        head.next.next.next.next.val = 5;
        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.val = 6;
        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.val = 7;
        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.val = 8;

        head = f(head, 2);
        print(head);

        System.out.println(String.format("%04d", 1));
    }

}
