package advance.datastructureandalgorithm.map;/**
 * Created by lt on 2019/1/31.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName HashMapTest
 * @Descrption TODO
 * @Author lt
 * @Date 2019/1/31 15:27
 * @Version 1.0
 **/
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null, 111);
        map.put(null, 222);
        System.out.println(map.get(null));
        map.put(123, null);
        map.put(123, null);
        System.out.println(map.get(123));
        System.out.println(map.toString());

        Set set = new HashSet<Object>();
        set.add(null);
        set.add(null);
        set.add(1);
        System.out.println(set.toString());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.put(111, null);
        System.out.println(concurrentHashMap.toString());
//        StringBuffer

    }

}
