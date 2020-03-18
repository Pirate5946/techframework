package advance.datastructure.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/2/20 下午9:33
 **/
public class linkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(8);

        for (int i = 0; i < 5; i++) {
            linkedHashMap.put(i, i + "");
        }
        Set<Map.Entry<Integer, String>> entrySet = linkedHashMap.entrySet();
        for (Map.Entry<Integer, String> entry : entrySet) {
            System.out.println(entry.getKey() + " :" + entry.getKey());

        }
    }
}
