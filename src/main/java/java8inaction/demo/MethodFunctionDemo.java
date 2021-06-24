package java8inaction.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import java8inaction.entity.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * create at : 2020/8/16 18:21
 * description: .
 *
 * @author : LIUTAO
 **/
public class MethodFunctionDemo {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

//        List<Apple> appleList = new ArrayList<>(new Apple(80,"green"),
//                new Apple(155, "green"),
//                new Apple(120, "red"));

        //test1();

        List<String> list = Arrays.asList("one", "two", "three", "four");
        List<String> collect = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        collect.stream().peek(e -> list.contains(e));
    }

    private static void test1() {
        List<Integer> dimensionValueList = Arrays.asList(1, 2, 3);
        List<Integer> dimensionFilterValues = Arrays.asList(1, 2,3,4);

        List<Integer> collect = dimensionValueList.stream().filter(item -> !dimensionFilterValues.contains(item)).collect(Collectors.toList());
//        collect.parallelStream().forEach(System.out::println);
//        dimensionValueList.removeAll(dimensionFilterValues);
        System.out.println(collect);
        System.out.println(JSONUtil.toJsonStr(collect));
        System.out.println(CollUtil.join(collect, ","));
        collect.parallelStream().forEach(System.out::println);
    }
}
