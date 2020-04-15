package advance.datastructureandalgorithm.array.binarysearch;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * description: 在有序二维数组查找目标元素
 *  @see <a href="https://www.nowcoder.com/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?f=discussion">解题思路讨论</a>
 *
 * @author : LIUTAO
 * create at : 2020/3/26 上午10:41
 **/
public class FindNumberInSortedTwoDimensionalArray {

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = RandomUtil.randomInt(0, 10);
                System.out.println(StrUtil.format("array[{}][{}] : {}", i, j, array[i][j]));
            }
        }
        boolean b = find(array, 6);
        System.out.println(b);
    }

    public static boolean find(int[][] array, int target) {

        for (int i = 0; i < array.length; i++) {
            int low = 0;
            int high = array[i].length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (target == array[i][mid]) {
                    return true;
                } else if (target < array[i][mid]) {
                    high = mid - 1;
                } else if (target > array[i][mid]) {
                    low = mid + 1;
                }
            }

        }

        return false;
    }
}
