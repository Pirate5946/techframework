package advance.datastructureandalgorithm.array;

/**
 * description: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author : LIUTAO
 * create at : 2020/4/16 下午3:02
 **/
public class ReOrderArray {
    public static void main(String[] args) {
        int[] array = new int[16];
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }

    public void reOrderArray(int [] array) {
        int oddCount = 0;
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                newArray[oddCount] = array[i];
                oddCount++;
            }
        }
        int evenCount = 0;
        for(int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 0) {
                newArray[oddCount + evenCount] = array[i];
                evenCount++;
            }
        }
        for(int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
