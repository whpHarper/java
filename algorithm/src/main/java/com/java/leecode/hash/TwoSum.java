package com.java.leecode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TwoSum
 * @Description 求数组中两个数之和等于target
 * @Author whp
 * @Date 2023/1/3
 * @Version 1.0
 **/
public class TwoSum {
    public int[] getSum(int[] args,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<args.length;i++){
            if(map.containsKey(target-args[i])){
                return new int[]{map.get(target-args[i]),i};
            }
            map.put(args[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums= {1,2,3,4,5};
        TwoSum twoSum=new TwoSum();
        int[] myNum = twoSum.getSum(nums, 9);
        for (int num: myNum) {
            System.out.println(nums[num]);
        }
    }
}
