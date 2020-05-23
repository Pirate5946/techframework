package advance.datastructureandalgorithm.array;

/**
 * description: 获取数组中第二小的数，不使用排序
 *
 * @author : LIUTAO
 * create at : 2020/5/21 下午6:17
 **/
public class GetSecondMinNumber {
    public static void main(String[] args) {
        int[] intArr = new int[]{1, 2, 3, 4, 5};

        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i : intArr) {
            if (i < min) {
                min = i;
            } else if (i < secondMin && i != min) {
                secondMin = i;
            }
        }
        System.out.println(secondMin);
    }
}
