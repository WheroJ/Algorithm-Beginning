package com.wheroj.algorithm.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum/
 * 路径总和
 */
public class BinaryTreePathSum {

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

    static boolean isSum = false;

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        f(root, 0, targetSum);
        return isSum;
    }

    private static void f(TreeNode node, int preSum, int targetSum) {
        if (node.left == null && node.right == null) {
            if (targetSum == preSum + node.val) {
                isSum = true;
            }
            return;
        }

        if (node.left != null) {
            f(node.left, preSum + node.val, targetSum);
        }

        if (node.right != null) {
            f(node.right, preSum + node.val, targetSum);
        }
    }

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        g(root, new ArrayList<>(), 0, targetSum, ans);
        return ans;
    }

    private static void g(TreeNode node, List<Integer> path, int preSum, int targetSum, List<List<Integer>> ans) {
        if (node.left == null && node.right == null) {
            if (preSum + node.val == targetSum) {
                path.add(node.val);
                ans.add(copyArray(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        path.add(node.val);
        if (node.left != null) {
            g(node.left, path, preSum + node.val, targetSum, ans);
        }

        if (node.right != null) {
            g(node.right, path, preSum + node.val, targetSum, ans);
        }
        path.remove(path.size() - 1);
    }

    private static List<Integer> copyArray(List<Integer> list) {
        List<Integer> copy = new ArrayList<>();
        list.stream().forEach(item->copy.add(item));
        return copy;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        root.right.right.left = new TreeNode(5);

        List<List<Integer>> lists = pathSum(root, 22);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(hasPathSum(root, 5));
    }

}
