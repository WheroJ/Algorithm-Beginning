package com.wheroj.algorithm.data_structure;

public class SingleNodeTest {

    static class Node {
        private Integer value;
        private Node next;

        public Node(Integer value) {
            super();
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static void print(Node header) {
        while (header != null) {
            System.out.println(header.value);
            header = header.next;
        }
    }

    private static Node f(Node header) {
        header = header.next;
        return header;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        print(head);
        print(f(head));
        System.out.println(head.value);
    }
}
