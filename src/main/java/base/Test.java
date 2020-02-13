package base;

import cn.hutool.cache.CacheUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName Test
 * @Descrption TODO
 * @Author lt
 * @Date 2019/11/10 0:06
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
//        String pnr = "R1RT HE0RTQW";
//        System.out.println(pnr.indexOf("RT"));

//        CacheUtil
//        HashMap

        // 测试 put 方法
//        HashMap<String, Integer> hashMap = new HashMap<>(1);
//        hashMap.put("1", 1);
//        Integer put = hashMap.put("1", 11);
//        hashMap.put("2", 2);
//        hashMap.put("3", 3);
//        System.out.println(put);
//        System.out.println(hashMap.toString());

        // 测试 get 方法
        Map<String, Integer> map = new HashMap<String, Integer>(1);
        Integer put = map.put("1", 1);
        Integer integer = map.get("1");
        System.out.println(integer);
    }
}
