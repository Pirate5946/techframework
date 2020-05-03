package advance.sort;

import cn.hutool.core.util.StrUtil;

/**
 * description: 堆排序
 *
 * @author : LIUTAO
 * create at : 2020/4/12 下午1:58
 * @see <a href="https://blog.csdn.net/nupt123456789/article/details/21370455">Java 堆排序思路</a>
 *
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 *
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 **/
public class HeapSortTopK {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int []a = {12,5,6,98,45,2,4,9};
        findMaxTopK(a, 8);
        for(int e:a){
            System.out.println(e);
        }
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
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            minHeapify(data, 0, i - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(StrUtil.format("获取最小Top {}结束，耗时：{}秒", k, (endTime - startTime) / 1000.0));
    }

    /**
     * 最大堆调整
     *
     * @param data
     * @param low
     * @param high
     */
    private static void maxHeapify(int[] data, int low, int high) {
        // TODO Auto-generated method stub
        int left = 2 * low + 1;
        int right = 2 * low + 2;
        int largest = low;
        if (left <= high && data[left] > data[largest]) {
            largest = left;
        }
        if (right <= high && data[right] > data[largest]) {
            largest = right;
        }
        if (largest != low) {
            int temp = data[low];
            data[low] = data[largest];
            data[largest] = temp;
            maxHeapify(data, largest, high);
        }
    }

    /**
     * 最小堆调整
     *
     * @param data
     * @param low
     * @param high
     */
    private static void minHeapify(int[] data, int low, int high) {
        // TODO Auto-generated method stub
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
