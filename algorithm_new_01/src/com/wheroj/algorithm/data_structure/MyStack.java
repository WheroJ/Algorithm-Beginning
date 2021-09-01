package com.wheroj.algorithm.data_structure;

public class MyStack {
    static class LocalStack<T> {
        LinkedReversal.Node<T> head;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public T push(T value) {
            LinkedReversal.Node<T> cur = new LinkedReversal.Node<T>(value);
            if (head != null) {
                cur.next = head;
            }
            head = cur;
            size++;
            return value;
        }

        public T pull() {
            T ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        LocalStack<Integer> stack = new LocalStack<>();
        stack.push(4);
        stack.push(7);
        stack.push(3);
        System.out.println(stack.getSize());
        System.out.println(stack.pull());
        System.out.println(stack.pull());
        System.out.println(stack.pull());
        System.out.println(stack.pull());
        System.out.println(stack.getSize());
    }
}
