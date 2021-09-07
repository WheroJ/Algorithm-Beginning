package com.wheroj.algorithm.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrderTraversalTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tempNode = queue.poll();
                row.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }

                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(10);

        List<List<Integer>> lists = levelOrder(root);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

}
