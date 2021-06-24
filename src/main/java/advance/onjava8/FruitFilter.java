package advance.onjava8;

/**
 * create at : 2020/9/29 21:13
 * description: .
 *
 * @author : LIUTAO
 **/
public interface FruitFilter {
    /**
     * 过滤水果
     * @param fruit 水果
     * @return true/false
     */
    boolean filter(Fruit fruit);
}
