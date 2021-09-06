package com.wheroj.algorithm.binary_tree;

import com.wheroj.algorithm.data_structure.KReversalLinked;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {

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

    /**
     * 每一颗子树都应该是平衡树
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 首先判断当前节点是否是平衡树
        boolean centerBalanced = Math.abs(treeMaxHigh(root.left) - treeMaxHigh(root.right)) <= 1;
        if (centerBalanced) {
            // 当前节点是平衡树，再判断left和right节点是否是平衡树
            boolean leftBalanced = true, rightBalanced = true;
            if (root.left != null) {
                leftBalanced = isBalanced(root.left);
            }
            if (root.right != null) {
                rightBalanced = isBalanced(root.right);
            }
            return leftBalanced && rightBalanced;
        }
        return false;
    }

    /**
     * 题目：任意子树，左树的最大值小于x，右树的最小值大于x，则是搜索二叉树
     * 每一颗子树都应该是搜索二叉树
     * @return
     */
    /*public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 首先判断当前节点是否是搜索二叉树
        boolean centerSearched = treeMaxVal(root.left) < root.val && treeMinVal(root.right) > root.val;
        if (centerSearched) {
            // 当前节点是搜索二叉树，再判断left和right节点是否是搜索二叉树
            boolean leftSearched = true, rightSearched = true;
            if (root.left != null) {
                leftSearched = isValidBST(root.left);
            }
            if (root.right != null) {
                rightSearched = isValidBST(root.right);
            }
            return leftSearched && rightSearched;
        }
        return false;
    }*/

    private static int treeMaxHigh(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = treeMaxHigh(node.left) + 1;
        int right = treeMaxHigh(node.right) + 1;
        return Math.max(left, right);
    }

    private static int treeMaxVal(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int leftVal = treeMaxVal(node.left);
        int rightVal = treeMaxVal(node.right);
        int curVal = node.val;
        return Math.max(curVal, Math.max(leftVal, rightVal));
    }

    private static int treeMinVal(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int leftVal = treeMinVal(node.left);
        int rightVal = treeMinVal(node.right);
        int curVal = node.val;
        return Math.min(curVal, Math.min(leftVal, rightVal));
    }

    static class Info {
        Integer min;
        Integer max;
        boolean isBST;
    }

    public static boolean isValidBST(TreeNode root) {
        return process(root, 0, 0).isBST;
    }

    private static Info process(TreeNode root, int preMin, int preMax) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left, preMin, preMax);
        Info rightInfo = process(root.right, preMin, preMax);

        Info rootInfo = new Info();
        if (leftInfo != null) {
            rootInfo.max = preMax;
        }
        if (rightInfo != null) {
        }
        return rootInfo;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.println(treeMaxHigh(treeNode));
        System.out.println(isBalanced(treeNode));

        treeNode = new TreeNode(20);
        treeNode.left = new TreeNode(10);
        treeNode.right = new TreeNode(29);
        treeNode.left.left = new TreeNode(9);
        treeNode.right.left = new TreeNode(23);
        treeNode.right.right = new TreeNode(30);
        System.out.println(isValidBST(treeNode));
    }

}
