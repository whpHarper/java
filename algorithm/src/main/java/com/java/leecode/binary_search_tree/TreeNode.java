package com.java.leecode.binary_search_tree;

/**
 * @ClassName TreeNode
 * @Description 树节点
 * @Author whp
 * @Date 2023/1/3
 * @Version 1.0
 **/
public class TreeNode {
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value){
        this.val=value;
    }
}
