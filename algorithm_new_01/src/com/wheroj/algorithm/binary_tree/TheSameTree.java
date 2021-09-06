package com.wheroj.algorithm.binary_tree;

/**
 * https://leetcode-cn.com/problems/same-tree/submissions/  相同的树
 * https://leetcode-cn.com/problems/symmetric-tree/  对称树
 */
public class TheSameTree {

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
     * 是否是相同的树
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null) ^ (q == null)) {
            return false;
        }

        if (p == null && q == null) {
            return true;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }

        if (p == null && q == null) {
            return true;
        }

        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    public static void main(String[] args) {
        TreeNode p0 = new TreeNode(1);
        p0.left = new TreeNode(2);
        p0.right = new TreeNode(1);

        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(1);
        p1.right = new TreeNode(2);

        System.out.println(isSameTree(p0, p1));
    }

}
