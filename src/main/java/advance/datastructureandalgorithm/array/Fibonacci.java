package advance.datastructureandalgorithm.array;

/**
 * description: 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。 n<=39
 *
 * @author : LIUTAO
 * create at : 2020/4/14 下午5:16
 **/
public class Fibonacci {

    public int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int n1 = 0;
        int n2 = 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }
        return result;
    }
}
