package advance.datastructureandalgorithm.sort;

import advance.datastructureandalgorithm.TopK;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;

/**
 * description: 堆排序
 *
 * @author : LIUTAO
 * create at : 2020/4/12 下午1:58
 * <p>
 *  @see <a href="https://blog.csdn.net/nupt123456789/article/details/21370455">Java 堆排序思路</a>
 * </p>
 *
 * <p>
 *  @see <a href="https://blog.csdn.net/jisuanjiguoba/article/details/80854854">数据结构之图解堆排序（Java实现）</a>
 * </p>
 *
 * <p>
 *   大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * </p>
 * <p>
 *   小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 * </p>
 *
 * 一般升序采用大顶堆，降序采用小顶堆
 **/
public class HeapSort {
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("读取文件开始");
        long startTime = System.currentTimeMillis();

        int[] intArray = TopK.getIntArrayFromFile();

        long endTime = System.currentTimeMillis();
        System.out.println(StrUtil.format("读取文件结束，耗时：{}秒,开始排序", (endTime - startTime) / 1000.0));

        // 快速排序
//        TopK.getTopK();

        // 堆排序 处理 topK
        findMinTopK(intArray, 10);
//        myHeapSort(intArray);
//        HeapSortTopK.findMinTopK(intArray, 10);

        for(int i = intArray.length - 1; i >= intArray.length - 10; i--){
            System.out.println(intArray[i]);
        }



    }

    /**
     * 最大堆调整
     * 将data当中范围为[i~high]的元素调整为
     * 以i为根的子树调整为最大堆
     *
     * @param data
     * @param i
     * @param high
     */
    public static void maxHeapify(int[] data, int i, int high) {
        int left = 2 * i + 1;//左子树
        int right = 2 * i + 2;//右子树
        int largest = i;//定义一个最大值
        if (left <= high && data[left] > data[i]) {
            largest = left;
        }
        if (right <= high && data[right] > data[largest]) {
            largest = right;
        }
        if (largest != i) {//如果最大值不是根节点，将根节点和最大值交换
            int temp = data[i];
            data[i] = data[largest];
            data[largest] = temp;
            //由于交换，可能其子树不满足最大堆，继续向后调整，使其子树成为最大堆
            maxHeapify(data, largest, high);
        }
    }

    /**
     * 堆排序
     * 1.建立堆
     * 2.逐步输出堆中的最大值
     *
     * @param data
     */
    public static void myHeapSort(int[] data) {
        long startTime = System.currentTimeMillis();
        int n = data.length;
        //建立堆，由于n/2及以后的元素肯定是叶子节点，可看为大小为1的最大堆
        //因此从最后一个叶子节点逐步向前调整
        //调整结果：data[0]为根节点的最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(data, i, n - 1);
        }
        //此时，data是一个data[0]为根节点的最大堆
        //逐步将data[0]与最后一个元素交换，然后再将0~n-2调整为最大堆
        //相当于逐步将堆的最大元素放到数组最后的过程
        for (int i = n - 1; i >= 1; i--) {
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            maxHeapify(data, 0, i - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("myHeapSort 排序时间(秒) : " + (endTime - startTime) / 1000.0);
    }

    /**
     * 查找data中的最大的前K个值
     *
     * @param data
     * @param k
     */
    public static void findMaxTopK(int[] data, int k) {
        long startTime = System.currentTimeMillis();
        if (k >= data.length) {
            return;
        }
        int n = data.length;
        for (int i = n / 2 - 1; i >= 0; i--) {//建堆的时间复杂度为O(n)
            maxHeapify(data, i, n - 1);
        }
        for (int i = n - 1; i >= n - k; i--) {//只需要调整k次即可
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            maxHeapify(data, 0, i - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(StrUtil.format("获取最大Top {}结束，耗时：{}秒", k, (endTime - startTime) / 1000.0));
    }

    /**
     * 查找data中的最小的前K个值
     *
     * @param data
     * @param k
     */
    public static void findMinTopK(int[] data, int k) {

        long startTime = System.currentTimeMillis();
        if (k >= data.length) {
            return;
        }
        int n = data.length;
        //建堆的时间复杂度为O(n)
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(data, i, n - 1);
        }

        //只需要调整k次即可
        for (int i = n - 1; i >= n - k; i--) {
//            System.out.println(data[0]);
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            minHeapify(data, 0, i - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(StrUtil.format("获取最小Top {}结束，耗时：{}秒", k, (endTime - startTime) / 1000.0));
    }

    /**
     * 最小堆调整
     *
     * @param data
     * @param low
     * @param high
     */
    private static void minHeapify(int[] data, int low, int high) {
        int left = 2 * low + 1;
        int right = 2 * low + 2;
        int minimum = low;
        if (left <= high && data[left] < data[minimum]) {
            minimum = left;
        }
        if (right <= high && data[right] < data[minimum]) {
            minimum = right;
        }
        if (minimum != low) {
            int temp = data[low];
            data[low] = data[minimum];
            data[minimum] = temp;
            minHeapify(data, minimum, high);
        }
    }

}
