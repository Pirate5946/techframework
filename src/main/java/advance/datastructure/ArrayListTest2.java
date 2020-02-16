package advance.datastructure;/**
 * Created by lt on 2019/1/31.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ArrayListTest2
 * @Descrption TODO
 * @Author lt
 * @Date 2019/1/31 17:00
 * @Version 1.0
 **/
public class ArrayListTest2 {
    public static void main(String[] args) {
//        ArrayList<Object> arrayList = new ArrayList<>(1);
//        arrayList.add(null);
//        arrayList.add(null);
//        System.out.println(arrayList.get(0));
//        System.out.println(arrayList.get(1));

        listAddSet();
    }

    public static void listAddSet() {
        List<Integer> list = new ArrayList<>(3);
        list.add(1);

        Set<Integer> set = new HashSet<>(1);
        set.add(2);

        list.addAll(set);
        System.out.println(list.toString());

    }
}
