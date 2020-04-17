package advance.datastructureandalgorithm.algorithm.recursion;

/**
 * description: 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * @author : LIUTAO
 * create at : 2020/4/15 下午3:48
 **/
public class Power {

    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            return base;
        } else if (exponent < 0) {
            double d = power(base, ++exponent);
            return 1 / base * d;
        } else {
            double d = power(base, --exponent);
            return base * d;
        }
    }
}
