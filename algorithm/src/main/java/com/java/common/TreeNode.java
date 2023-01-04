package com.java.common;

/**
 * @ClassName TreeNode
 * @Description 树节点
 * @Author whp
 * @Date 2022/12/26
 * @Version 1.0
 **/
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value){
        this.value=value;
    }

    public TreeNode(){
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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




}
