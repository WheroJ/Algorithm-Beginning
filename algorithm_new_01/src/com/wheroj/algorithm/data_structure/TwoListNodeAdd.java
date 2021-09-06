package com.wheroj.algorithm.data_structure;

/**
 * https://leetcode-cn.com/problems/sum-lists-lcci/
 */
public class TwoListNodeAdd {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode add(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        int size1 = getListNodeSize(l1);
        int size2 = getListNodeSize(l2);

        // 进位
        int carry = 0;
        ListNode l = size1 > size2 ? l1 : l2;
        ListNode s = l == l1 ? l2 : l1;

        ListNode last = l;
        while (l != null && s != null) {
            int sum = l.val + s.val + carry;
            l.val = sum % 10;
            carry = sum / 10;

            last = l;
            l = l.next;
            s = s.next;
        }

        while (l != null) {
            int sum = l.val + carry;
            l.val = sum % 10;
            carry = sum / 10;
            last = l;
            l = l.next;
        }

        // 两个链表都遍历完之后，有余数就进位
        if (carry != 0) {
            last.next = new ListNode();
            last.next.val = carry;
        }
        return size1 > size2 ? l1 : l2;
    }

    private static int getListNodeSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private static ListNode generateListNode(int size, int maxVal) {
        ListNode head = new ListNode();
        head.val = (int)(Math.random() * maxVal);

        ListNode node = head;
        while (--size > 0) {
            ListNode newNode = new ListNode();
            newNode.val = (int)(Math.random() * (maxVal % 10));
            node.next = newNode;
            node = newNode;
        }
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
        ListNode head1 = generateListNode(3, 6);
        ListNode head2 = generateListNode(3, 9);
        print(head1);
        print(head2);
        print(add(head1, head2));
    }
}
