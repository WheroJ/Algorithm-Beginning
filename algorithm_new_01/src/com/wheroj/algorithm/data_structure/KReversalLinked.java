package com.wheroj.algorithm.data_structure;

public class KReversalLinked {

    private static LinkedReversal.Node<Integer> getK(LinkedReversal.Node<Integer> start, int K) {
        if (K == 0 || start == null) {
            return start;
        }

        LinkedReversal.Node<Integer> end = start;
        while ((--K) >= 0 && end != null) {
            end = start.next;
            start = end;
        }
        return end;
    }

    private static LinkedReversal.Node<Integer> reversal(
    		LinkedReversal.Node<Integer> start, 
    		LinkedReversal.Node<Integer> end) {
        if (start == null || end == null) {
            return start;
        }

        if (start == end) {
            return start;
        }

        LinkedReversal.Node<Integer> pre = null;
        LinkedReversal.Node<Integer> next;
        LinkedReversal.Node<Integer> cur = start;
        while (cur != null && cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static LinkedReversal.Node<Integer> f(LinkedReversal.Node<Integer> start, int K) {
        LinkedReversal.Node<Integer> stageEnd = getK(start, K);
        LinkedReversal.Node<Integer> pre = null;
        while (stageEnd != null) {
            System.out.println(stageEnd.value);
//            if (pre == null) {
//            } else {
//                reversal(node, stageEnd);
//            }
            reversal(start, stageEnd);
            print(pre);
            start = stageEnd;
            stageEnd = getK(stageEnd, K);
        }
        return pre;
    }

    private static void print(LinkedReversal.Node<Integer> header) {
        while (header != null) {
            System.out.print(header.value);
            header = header.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedReversal.Node<Integer> head = new LinkedReversal.Node<Integer>(1);
        head.next = new LinkedReversal.Node<>(2);
        head.next.next = new LinkedReversal.Node<>(3);
        head.next.next.next = new LinkedReversal.Node<>(4);
        head.next.next.next.next = new LinkedReversal.Node<>(5);
        head.next.next.next.next.next = new LinkedReversal.Node<>(6);
        head.next.next.next.next.next.next = new LinkedReversal.Node<>(7);
        head.next.next.next.next.next.next.next = new LinkedReversal.Node<>(8);

        f(head, 3);
        print(head);
//        System.out.println(getK(head, 3).value);
    }
}
