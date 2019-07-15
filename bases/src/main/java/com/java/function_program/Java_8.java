package com.java.function_program;

import org.junit.Before;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author whp 19-7-15
 */
public class Java_8 {
    private List<Product> products = new ArrayList<>();

    @Before
    public void init() {
        products.add(new Product(1, "苹果手机", 8888.88f,"手机"));//注意:要给Product类加一个分类名称dirName字段
        products.add(new Product(2, "华为手机", 6666.66f,"手机"));
        products.add(new Product(3, "联想笔记本", 7777.77f,"电脑"));
        products.add(new Product(4, "机械键盘", 999.99f,"键盘"));
        products.add(new Product(5, "雷蛇鼠标", 222.22f,"鼠标"));
    }

    public static void main(String[] args){


        /*1. Stream 创建
         *
         */
        //流创建 collection
        List<String> list= Arrays.asList("a","b","c");
        Stream<String> stream=list.stream();
        Stream<String> paralleStream=list.parallelStream();


        // Arrays.stream()
        IntStream intStream=Arrays.stream(new int[]{1,2,3});
        Stream<Integer> integerStream=Arrays.stream(new Integer[]{1,2,3});

        //stream.of()
        Stream<Integer> integerStream1=Stream.of(1,2,3);

        //迭代无限流
//        Stream.iterate(1,n->n+1).limit(100).forEach(System.out::println);

        /**2. Stream 处理
         * 过滤，去重，limit，跳跃
         */
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(i->i%2==0).forEach(System.out::println);
        Arrays.asList(1,2,1,3,3,2,4,6).stream().filter(i->i%2==0).distinct().forEach(System.out::println);

        /**3. 映射
         *
         */
        Stream<String> stram=Arrays.asList("I","love","you").stream();
        stram.map(s->s.toUpperCase()).forEach(System.out::println);
        Stream<List<String>>listStream=Stream.of(Arrays.asList("H","E"),Arrays.asList("L","L","O"));
        listStream.flatMap(list1->list1.stream()).forEach(System.out::println);

        /*4. 排序
         *
         */
        System.out.println("====================自然排序============================");
        Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().sorted().forEach(System.out::print);
        System.out.println("====================定制排序============================");
        Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().sorted((x,y) -> y.compareTo(x)).forEach(System.out::print);

        /**5. 匹配查找
         *
         */
        System.out.println("======================检查是否匹配所有==========================");
        boolean allMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().allMatch(x-> x>0);
        System.out.println(allMatch);
        System.out.println("======================检查是否至少匹配一个元素====================");
        boolean anyMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().anyMatch(x -> x>7);
        System.out.println(anyMatch);
        System.out.println("======================检查是否没有匹配的元素======================");
        boolean noneMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().noneMatch(x -> x >10);
        System.out.println(noneMatch);
        System.out.println("======================返回第一个元素==========================");
        Optional<Integer> first = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().findFirst();
        System.out.println(first.get());
        System.out.println("======================返回当前流中的任意元素=======================");
        Optional<Integer> any = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().findAny();
        System.out.println(any.get());

        /**6. 统计
         *
         */
        long count = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().count();
        System.out.println(count);
        Optional<Integer> max = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().max((x,y) -> x.compareTo(y));
        System.out.println(max.get());
        Optional<Integer> min = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().min((x,y) -> x.compareTo(y));
        System.out.println(min.get());

        /**7. 规约：将流中的元素挨个结合起来，得到一个值
         *
         */
        System.out.println("=====reduce:将流中元素反复结合起来，得到一个值==========");
        Stream<Integer> stream1 = Stream.iterate(1, x -> x+1).limit(100);
        //stream.forEach(System.out::println);
        Integer sum = stream1.reduce(0,(x,y)-> x+y);
        System.out.println(sum);

        /**8. 汇总：将流转换为其他形式
         *
         */
        System.out.println("=====collect:将流转换为其他形式:list");
        List<Integer> list1 = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println("=====collect:将流转换为其他形式:set");
        Set<Integer> set = Arrays.asList(1, 1, 2, 2, 3, 3, 3).stream().collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("=====collect:将流转换为其他形式:TreeSet");
        TreeSet<Integer> treeSet = Arrays.asList(1, 1, 2, 2, 3, 3, 3).stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
        System.out.println("=====collect:将流转换为其他形式:map");
        Map<Integer, Integer> map = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.toMap(Integer::intValue, Integer::intValue));
        System.out.println(map);
        System.out.println("=====collect:将流转换为其他形式:sum");
        Integer sum1 = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum1);
        System.out.println("=====collect:将流转换为其他形式:avg");
        Double avg = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(avg);
        System.out.println("=====collect:将流转换为其他形式:max");
        Optional<Integer> max1 = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(max1.get());
        System.out.println("=====collect:将流转换为其他形式:min");
        Optional<Integer> min1 = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.minBy((x,y) -> x-y));
        System.out.println(min1.get());

        /**9. 分组和分区
         *
         */
       /* System.out.println("=======根据商品分类名称进行分组==========================");
        Map<String, List<Product>> map = products.stream().collect(Collectors.groupingBy(Product::getDirName));
        System.out.println(map);
        System.out.println("=======根据商品价格范围多级分组==========================");
        Map<Double, Map<String, List<Product>>> map2 = products.stream().collect(Collectors.groupingBy(
                Product::getPrice, Collectors.groupingBy((p) -> {
                    if (p.getPrice() > 1000) {
                        return "高级货";
                    } else {
                        return "便宜货";
                    }
                })));
        System.out.println(map2);*/
    }

}
