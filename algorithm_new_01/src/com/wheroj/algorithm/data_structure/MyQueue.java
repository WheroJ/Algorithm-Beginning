package com.wheroj.algorithm.data_structure;

public class MyQueue {
    static class LocalQueue<T> {
        LinkedReversal.Node<T> start;
        LinkedReversal.Node<T> end;
        int size = 0;

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public T push(T value) {
            LinkedReversal.Node<T> cur = new LinkedReversal.Node<>(value);
            if (start == null) {
                start = cur;
            } else {
                end.next = cur;
            }
            end = cur;
            size++;
            return value;
        }

        public T pull() {
            T ans = null;
            if (start != null) {
                ans = start.value;
                start = start.next;
                size--;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        LocalQueue<Integer> queue = new LocalQueue<>();
        queue.push(3);
        queue.push(6);
        queue.push(2);
        System.out.println(queue.getSize());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.getSize());
    }
}
