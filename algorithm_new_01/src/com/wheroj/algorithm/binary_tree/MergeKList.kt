package com.wheroj.algorithm.binary_tree

import java.util.*

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
object MergeKList {

    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     **/
     class ListNode(var `val`: Int) {
        var next: ListNode? = null
     }

    class NodeComparator : Comparator<ListNode> {
        override fun compare(p0: ListNode?, p1: ListNode?): Int {
            if (p0 == null && p1 == null) {
                return 0;
            }

            if (p0 == null) {
                return -1;
            }

            if (p1 == null) {
                return  1;
            }
            return p0.`val` - p1.`val`
        }

    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val queue = PriorityQueue (NodeComparator())
        lists.forEach { node ->
            run {
                if (node != null) {
                    queue.add(node)
                }
            }
        }

        val head = queue.poll();

        var pre = head
        while (!queue.isEmpty()) {
            if (pre.next != null) {
                queue.add(pre.next);
            }
            val listNode = queue.poll()
            pre.next = listNode;
            pre = listNode;
        }
        return head;
    }

    internal fun print(header: ListNode?) {
        var  head = header
        while (head != null) {
            print("".plus(head.`val`).plus(" "))
            head = head.next
        }
        println()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lists = arrayOfNulls<ListNode?>(3)

        var node = ListNode(1)
        node.next = ListNode(5)
        (node.next)?.next = ListNode(7)
        ((node.next)?.next)?.next = ListNode(9)
        (((node.next)?.next)?.next)?.next = ListNode(11)
        lists[0] = node

        node = ListNode(2)
        node.next = ListNode(8)
        (node.next)?.next = ListNode(10)
        ((node.next)?.next)?.next = ListNode(15)
        (((node.next)?.next)?.next)?.next = ListNode(16)
        lists[1] = node

        node = ListNode(6)
        node.next = ListNode(8)
        (node.next)?.next = ListNode(9)
        ((node.next)?.next)?.next = ListNode(10)
        (((node.next)?.next)?.next)?.next = ListNode(11)
        ((((node.next)?.next)?.next)?.next)?.next = ListNode(12)
        lists[2] = node

        print(mergeKLists(lists))
    }

}