package com.wheroj.algorithm.data_structure;

public class DoubleLinkedQueue {

    static class DoubleQueue<T> {
        LinkedReversal.DoubleNode<T> head;
        LinkedReversal.DoubleNode<T> tail;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public T headPush(T value) {
            LinkedReversal.DoubleNode<T> cur = new LinkedReversal.DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                head.last = cur;
                cur.next = head;
                head = cur;
            }
            size++;
            return value;
        }

        public T tailPush(T value) {
            LinkedReversal.DoubleNode<T> cur = new LinkedReversal.DoubleNode<>(value);
            if (tail == null) {
                head = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
            }
            tail = cur;
            size++;
            return value;
        }

        public T headPull() {
            T ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                if (head != null) {
                    head.last = null;
                }
            }
            return ans;
        }

        public T tailPull() {
            T ans = null;
            if (tail != null) {
                ans = tail.value;
                tail = tail.last;
                if (tail != null) {
                    tail.next = null;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        DoubleQueue<Integer> queue = new DoubleQueue<>();
        queue.headPush(1);
        queue.headPush(2);
        queue.tailPush(3);
        queue.headPush(5);

        for (int i = 0; i < queue.getSize(); i++) {
            System.out.println(queue.tailPull());
        }
        System.out.println();
    }
}
