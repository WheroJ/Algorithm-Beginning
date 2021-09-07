package com.wheroj.algorithm.binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class BuildBinaryTree {

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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
    }

    private static TreeNode f(int[] preorder, int L1, int R1,
                          int[] inorder, int L2, int R2, Map<Integer, Integer> inorderMap) {
        if (L1 > R1) {
            return null;
        }

        TreeNode treeNode = new TreeNode(preorder[L1]);
        if (L1 == R1) {
            return treeNode;
        }
        int find = inorderMap.get(preorder[L1]);

        treeNode.left = f(preorder, L1 + 1, L1 + (find - L2), inorder, L2, find -1,  inorderMap);
        treeNode.right = f(preorder, L1 + (find - L2) + 1, R1, inorder, find + 1, R2,  inorderMap);
        return treeNode;
    }

    /**
     * 前序：中左右
     * @param treeNode
     */
    private static void preTraversalTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.val + " ");
        preTraversalTree(treeNode.left);
        preTraversalTree(treeNode.right);
    }

    /**
     * 中序：左中右
     * @param treeNode
     */
    private static void inTraversalTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inTraversalTree(treeNode.left);
        System.out.print(treeNode.val + " ");
        inTraversalTree(treeNode.right);
    }

    /**
     * 后序：左右中
     * @param treeNode
     */
    private static void behindTraversalTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        behindTraversalTree(treeNode.left);
        behindTraversalTree(treeNode.right);
        System.out.print(treeNode.val + " ");
    }

    public static void main(String[] args) {
//        int[] preorder = new int[] { 3, 9, 20, 15, 7};
//        int[] inorder = new int[] { 9, 3, 15, 20, 7};
//        int[] preorder = new int[] { 1, 2, 3};
//        int[] inorder = new int[] { 3, 2, 1};
        int[] preorder = new int[] { 4, 3, 1, 2};
        int[] inorder = new int[] { 1, 2, 3, 4};

        TreeNode treeNode = buildTree(preorder, inorder);
        preTraversalTree(treeNode);
        System.out.println();
        inTraversalTree(treeNode);
        System.out.println();
        behindTraversalTree(treeNode);
    }

}
