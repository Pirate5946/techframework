package algorithm.sort;

import java.util.Arrays;

/**
 * description: 堆排序
 *
 * @author : LIUTAO
 * create at : 2020/4/12 下午1:58
 * @see <a href="https://blog.csdn.net/nupt123456789/article/details/21370455">Java 堆排序思路</a>
 * <p>
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * <p>
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 **/
public class HeapSortTopK_2 {


    //将array看成完全二叉树的顺序存储结构，建立大顶堆
    private static int[] buildMaxHeap(int[] array) {//从最后一个节点array.length-1的父节点开始一直到根节点，反复调整堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            changeDownToUp(array, i, array.length);
        }
        return array;
    }

    //将元素array[k]自下往上逐步调整树形结构
    private static void changeDownToUp(int[] array, int k, int length) {
        int temp = array[k];
        for (int i = 2 * k + 1; i <= length - 1; i = 2 * i + 1) {
            if (i + 1 < length && array[i] < array[i + 1]) {  //有右孩子且左孩子<右孩子
                i++;   //如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if (temp >= array[i]) {  //节点>=左右孩子中关键字较大者，调整结束
                break;
            } else {   //根节点 <左右子女中关键字较大者
                array[k] = array[i];  //将左右子结点中较大值array[i]调整到双亲节点上
                k = i; //修改k值，继续向下调整
            }
        }
        array[k] = temp;  //被调整的结点的值放在最终合适的位置
    }

    //堆排序
    public static int[] heapSort(int[] array) {

        array = buildMaxHeap(array); //初始建堆，array[0]为堆中最大的元素
        for (int i = array.length - 1; i >= 1; i--) {

            int temp = array[0];  //堆顶元素和堆底元素交换，得到当前最大元素正确的排序位置
            array[0] = array[i];
            array[i] = temp;
            changeDownToUp(array, 0, i);  //将剩余的元素整理成大顶堆
        }
        return array;
    }

    public static void main(String args[]) {
        int[] array = {82, 39, 68, 25, 16, 61, 59, 7, 116, 43};
        System.out.println("堆排序前:");
        System.out.println(Arrays.toString(array));
        System.out.println("堆排序后:");
        System.out.println(Arrays.toString(heapSort(array)));
    }

}
