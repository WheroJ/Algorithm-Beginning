package com.wheroj.algorithm.data_structure;

public class ValidBST {
	 public class TreeNode {
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
	 
	 static class Info{
		 int max;
		 int min;
		 boolean isBST;
	 }
	 
    public boolean isValidBST(TreeNode root) {
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
    	curInfo.min = node.val;
    	curInfo.max = node.val;
    	if (leftInfo != null) {
			curInfo.min = Math.min(leftInfo.min, curInfo.min);
			curInfo.max = Math.max(leftInfo.max, curInfo.max);
		}
    	
    	if (rightInfo != null) {
			curInfo.min = Math.min(rightInfo.min, curInfo.min);
			curInfo.max = Math.max(rightInfo.max, curInfo.max);
		}
    	
    	boolean isBTS = true;
    	if (leftInfo != null && rightInfo != null) {
			isBTS = leftInfo.max < node.val && rightInfo.min > node.val;
			isBTS = isBTS && leftInfo.isBST && rightInfo.isBST;
		} else if (leftInfo != null) {
			isBTS = leftInfo.max < node.val;
			isBTS = isBTS && leftInfo.isBST;
		} else if (rightInfo != null) {
			isBTS = rightInfo.min > node.val;
			isBTS = isBTS && rightInfo.isBST;
		}
    	curInfo.isBST = isBTS;

    	return curInfo;
    }
}
