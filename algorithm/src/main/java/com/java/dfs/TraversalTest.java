package com.java.dfs;

import com.java.common.BinaryTree;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TraversalTest
 * @Description TODO
 * @Author whp
 * @Date 2022/12/26
 * @Version 1.0
 **/
public class TraversalTest {
    public static void main(String[] args) {
        List<Integer> datas = Arrays.asList(8,7,4,10,9,10);
        BinaryTree binaryTree=new BinaryTree();
        BinaryTree binaryTree1 = binaryTree.createBinaryTree(datas);
        final List<Integer> dfsDatas = binaryTree.dfs(binaryTree1.getRoot());
        for(Integer data:dfsDatas){
            System.out.print(data+" ");
        }
    }
}
