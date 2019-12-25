package algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 牛客网 - 数组中重复的数字
 *
 * @author : LIUTAO
 * create at : 2019/12/22 上午11:37
 **/
public class DuplicateNumberInArray {

    public static void main(String[] args) {
        int[] numbers = {2, 1, 2};
        duplicate(numbers, numbers.length, new int[]{});
    }

    public static boolean duplicate(int[] numbers,int length,int [] duplication) {
        if (numbers == null || length < 0) {
            return false;
        }
        if (length <= 1) {
            return true;
        }
        Map<Integer, Integer> map = new HashMap<>(length);
        map.put(numbers[0], 1);
        for (int i = 1; i < length - 1; i++) {
            if(map.get(numbers[i]) == null) {
                map.put(numbers[i], 1);
            } else{
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;

    }
}
