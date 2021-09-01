package com.wheroj.algorithm.data_structure;

public class LinkedReversal {

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    static class DoubleNode<T> {
        T value;
        DoubleNode<T> next;
        DoubleNode<T> last;

        public DoubleNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public DoubleNode<T> getNext() {
            return next;
        }

        public void setNext(DoubleNode<T> next) {
            this.next = next;
        }

        public DoubleNode<T> getLast() {
            return last;
        }

        public void setLast(DoubleNode<T> last) {
            this.last = last;
        }
    }

    private static <T> void print(Node<T> header) {
        while (header != null) {
            System.out.println(header.value);
            header = header.next;
        }
    }

    private static <T> void printDouble(DoubleNode<T> header) {
        while (header != null) {
            System.out.println(header.value);
            header = header.next;
        }
    }

    /**
     * 逆序单链表
     * @param node
     * @param <T>
     * @return
     */
    private static <T> Node<T> reversalLinked(Node<T> node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node<T> cur = node;
        Node<T> pre = null;
        Node<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static <T> DoubleNode<T> reversalDoubleLinked(DoubleNode<T> node) {
        if (node == null || node.next == null) {
            return node;
        }

        DoubleNode<T> cur = node;
        DoubleNode<T> pre = null;
        DoubleNode<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.last = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
//        Node<Integer> node = new Node<>(1);
//        node.next = new Node<>(4);
//        node.next.next = new Node<>(3);
//        node.next.next.next = new Node<>(6);
//        print(node);
//        System.out.println("=======================");
//        node = reversalLinked(node);
//        print(node);

        DoubleNode<Integer> doubleNode = new DoubleNode<Integer>(5);
        doubleNode.last = null;
        doubleNode.next = new DoubleNode<>(7);
        doubleNode.next.last = doubleNode;
        doubleNode.next.next = new DoubleNode<>(6);
        doubleNode.next.next.last = doubleNode.next;
        doubleNode.next.next.next = new DoubleNode<>(3);
        doubleNode.next.next.next.next = null;
        doubleNode.next.next.next.last = doubleNode.next.next;
        printDouble(doubleNode);
        System.out.println("=======================");
        doubleNode = reversalDoubleLinked(doubleNode);
        printDouble(doubleNode);
    }
}
