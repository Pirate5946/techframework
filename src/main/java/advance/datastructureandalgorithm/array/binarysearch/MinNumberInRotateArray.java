package advance.datastructureandalgorithm.array.binarysearch;

/**
 * description: 在旋转后的递增数组中寻找最小的数字
 *
 * @author : LIUTAO
 * create at : 2020/4/13 下午4:01
 * @see <a href="https://www.nowcoder.com/questionTerminal/9f3231a991af4f55b95579b44b7a01ba?f=discussion">采用二分法解答这个问题</a>
 **/
public class MinNumberInRotateArray {

    public static int find(int[] array) {
        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            if (array[mid] > array[high]) {
                low = mid + 1;
            } else if (array[mid] == array[high]) {
                high = high - 1;
            } else {
                high = mid;
            }
        }
        return array[low];
    }
}
