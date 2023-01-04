package com.java.leecode.binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName BinarySearchTree
 * @Description 二叉查找树
 * @Author whp
 * @Date 2023/1/3
 * @Version 1.0
 **/
public class BinarySearchTree {

    /**
     * 前序遍历
     * @param root
     */
    public void dlr(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        while(stack!=null || root!=null){
            while (root!=null){
                System.out.println(root.val);
                stack.push(root);
                root=root.getLeft();
            }
            root= stack.pop();
            root=root.getRight();
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void ldr(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        while (!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.push(root);
                root=root.getLeft();
            }
            root=stack.pop();
            System.out.println(root.getVal());
            root=root.right;
        }
    }
    public void insert(TreeNode node,int val){
        TreeNode newNode=new TreeNode(val);
        if(node==null){
            return;
        }
        if(val>=node.getVal()){
            if(node.getRight()==null){
                node.setRight(newNode);
            }else{
                insert(node.getRight(),val);
            }
        }else{
            if(node.getLeft()==null){
                node.setLeft(newNode);
            } else {
                insert(node.getLeft(),val);
            }
        }
    }

    /**
     * 递归删除节点：
     * 1. 如果节点值>val,递归删除左侧节点，返回
     * 2. 如果节点值<val,递归删除右侧节点，返回
     * 3. 相等情况：
     *     3.1 如果左边节点为空，直接返回右侧节点
     *     3.2 如果右侧节点为空，直接返回左侧节点
     *     3.3 左右均不为空，则获取左侧节点最大值（左侧节点最右侧值），创建新节点，将该节点作为新节点，它的左侧节点为移除最大值的左侧，右侧为原来root节点的节点
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode delete(TreeNode root,int val){
        if(root==null){
            return null;
        }
        if(val> root.getVal()){
            root.right=delete(root.getRight(), val);
            return root;
        }
        if(val< root.getVal()){
            root.left=delete(root.getLeft(),val);
            return root;
        }
        if(root.left==null){
            return root.getRight();
        }
        if(root.right==null){
            return root.getLeft();
        }
        TreeNode replaceNode=getMax(root.getLeft());
        TreeNode replaceNodeCopy=new TreeNode(replaceNode.getVal());
        replaceNodeCopy.left=removeMax(root.getLeft());
        replaceNodeCopy.right=root.getRight();
        root.left=null;
        root.right=null;
        return replaceNodeCopy;
    }

    public TreeNode removeMax(TreeNode root){
        if(root.right==null){
            return root.left;
        }
        root.right=removeMax(root.right);
        return root;
    }
    public TreeNode getMax(TreeNode boot){
        if(boot==null){
            return boot;
        }
        while(boot!=null){
            boot=boot.right;
        }
        return boot;
    }

    /**
     * 验证是否是查询二叉树
     * @param root
     * @return
     */
    public boolean validBST(TreeNode root){
        if(root==null){
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        Queue<Integer> minQ=new LinkedList<>();
        Queue<Integer> maxQ=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp=queue.poll();
            Integer min=minQ.poll();
            Integer max=maxQ.poll();
            if(min!=null && min> temp.getVal()){
                return false;
            }
            if(max!=null && max< temp.getVal()){
                return false;
            }
            if(temp.getLeft()!=null){
                minQ.add(min);
                maxQ.add(temp.getVal());
                queue.add(temp.getLeft());
            }
            if(temp.getRight()!=null){
                maxQ.add(max);
                minQ.add(temp.getVal());
                queue.add(temp.getRight());
            }
        }
        return true;
    }
}
