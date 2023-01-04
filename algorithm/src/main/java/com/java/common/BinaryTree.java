package com.java.common;

import java.util.*;

/**
 * @ClassName BinaryTree
 * @Description 平衡二叉树
 * 二叉树的遍历：https://blog.csdn.net/weixin_42988712/article/details/109305212
 * @Author whp
 * @Date 2022/12/26
 * @Version 1.0
 **/
public class BinaryTree {
    private TreeNode root=null;

    public TreeNode getRoot() {
        return root;
    }

    //    树的节点已经不需要按顺序排好
    public void buildBinaryNode(TreeNode node,int data){
        //如果根结点是空,那么设置根结点,并且设置数据域
        if(this.root == null){
            this.root = new TreeNode(data);
        }else{
            /**
             * 根结点不为空,那么判断数据是否小于当前结点的数据
             */
            if(data < node.getValue()){
                //如果小于,判断当前结点是否有左叶子结点
                if(node.getLeft() == null){
                    //左叶子结点为空,设置左叶子结点,并且设置数据
                    node.setLeft(new TreeNode(data));
                }else{
                    //左叶子结点不为空,递归调用构建二叉树的函数
                    buildBinaryNode(node.getLeft(),data);
                }
            }else{
                //如果大于或等于,判断当前结点是否存在右叶子结点
                if(node.getRight()==null){
                    //右叶子结点为空,设置右叶子结点,并且设置数据域
                    node.setRight(new TreeNode(data));
                }else{
                    //右叶子几点不为空,递归调用构建二叉树的函数
                    buildBinaryNode(node.getRight(),data);
                }
            }
        }
    }

    /**
     * 深度优先探索
     * @param root
     * @return
     */
    public List<Integer> dfs(TreeNode root){
        List<Integer> values=new LinkedList<>();
        if(root==null){
            return null;
        }
        Stack<TreeNode> myStack=new Stack<>();
        myStack.add(root);
        while (!myStack.isEmpty()){
            TreeNode node=myStack.pop();
            values.add(node.getValue());
            //堆栈先进后出，所以先将右节点入栈，保证先pop出左节点
            if(node.getRight()!=null){
                myStack.push(node.getRight());
            }
            if(node.getLeft()!=null){
                myStack.push(node.getLeft());
            }
        }
        return values;
    }

    public List<Integer> bfs(TreeNode root){
        List<Integer> values=new LinkedList<>();
        if(root==null){
            return null;
        }
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);
        while(queue!=null){
            TreeNode node = queue.poll();
            values.add(node.getValue());
            if(node.getLeft()!=null){
                queue.add(node.getLeft());
            }
            if(node.getRight()!=null){
                queue.add(node.getRight());
            }
        }
        return values;
    }
    public BinaryTree createBinaryTree(List<Integer> datas){
        BinaryTree binaryTree=new BinaryTree();
        for(Integer data:datas){
            binaryTree.buildBinaryNode(binaryTree.getRoot(),data);
        }
        return binaryTree;
    }

}
