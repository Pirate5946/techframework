package advance.onjava8;


import java.util.ArrayList;
import java.util.List;

/**
 * create at : 2020/9/29 21:10
 * description: 测试接口传递, 行为参数化.
 *
 * @author : LIUTAO
 **/
public class FruitDemo {

    public static void main(String[] args) {


    }

    public List<Fruit> filterFruit(List<Fruit> fruitList, FruitFilter fruitFilter) {
        List<Fruit> filterList = new ArrayList<>(fruitList.size());
        for (Fruit fruit : fruitList) {
            if (fruitFilter.filter(fruit)) {
                filterList.add(fruit);
            }
        }
        return filterList;
    }
}
