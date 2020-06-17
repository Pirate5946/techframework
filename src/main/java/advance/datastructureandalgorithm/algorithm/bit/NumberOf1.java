package advance.datastructureandalgorithm.algorithm.bit;

/**
 * description: 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * @author : LIUTAO
 * create at : 2020/4/15 下午3:18
 **/
public class NumberOf1 {

    public static void main(String[] args) {
        numberOf1(1);
    }

    public static int numberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }
}
