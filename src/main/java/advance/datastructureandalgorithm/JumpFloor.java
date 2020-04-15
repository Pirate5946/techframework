package advance.datastructureandalgorithm;

/**
 * description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * <p>解法一：自底向上，使用迭代</p>
 * <p>解法二：自顶向下，使用递归</p>
 *
 * @author : LIUTAO
 * create at : 2020/4/14 下午5:24
 *
 **/
public class JumpFloor {

    public int jumpFloor(int target) {

        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return jumpFloor(target - 1) + jumpFloor(target - 2);
        }
    }


    public int jumpFloor2(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
}
