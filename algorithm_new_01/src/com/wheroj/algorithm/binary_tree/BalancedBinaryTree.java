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

    private static int treeMaxHigh(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = treeMaxHigh(node.left) + 1;
        int right = treeMaxHigh(node.right) + 1;
        return Math.max(left, right);
    }

    /**
     * 题目：任意子树，左树的最大值小于x，右树的最小值大于x，则是搜索二叉树
     * 每一颗子树都应该是搜索二叉树
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 首先判断当前节点是否是搜索二叉树
        int leftMax = treeMaxVal(root.left);
        int rightMin = treeMinVal(root.right);
        boolean leftBST = (leftMax == Integer.MIN_VALUE && root.left == null) || leftMax < root.val;
        boolean rightBST = (rightMin == Integer.MAX_VALUE && root.right == null) || rightMin > root.val;
        boolean centerSearched = leftBST && rightBST;
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

    /**
     * 此方法解法更优，时间复杂度低
     * 题目：任意子树，左树的最大值小于x，右树的最小值大于x，则是搜索二叉树
     * 每一颗子树都应该是搜索二叉树
     * @param root
     * @return
     */
    public static boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        Info curInfo = new Info();
        curInfo.max = node.val;
        curInfo.min = node.val;
        if (leftInfo != null) {
            curInfo.max = Math.max(curInfo.max, leftInfo.max);
            curInfo.min = Math.min(curInfo.min, leftInfo.min);
        }

        if (rightInfo != null) {
            curInfo.max = Math.max(curInfo.max, rightInfo.max);
            curInfo.min = Math.min(curInfo.min, rightInfo.min);
        }

        boolean isBST = true;
        if (leftInfo != null && rightInfo != null) {
            isBST = leftInfo.max < node.val && rightInfo.min > node.val && leftInfo.isBST && rightInfo.isBST;
        } else if (leftInfo != null) {
            isBST = leftInfo.max < node.val && leftInfo.isBST;
        } else if (rightInfo != null) {
            isBST = rightInfo.min > node.val && rightInfo.isBST;
        }
        curInfo.isBST = isBST;

        return curInfo;
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
