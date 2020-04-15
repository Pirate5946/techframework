package advance.datastructureandalgorithm;

/**
 * description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * @author : LIUTAO
 * create at : 2020/4/14 下午5:34
 *
 * @see <a href="https://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387?f=discussion">[编程题]变态跳台阶</a>
 **/
public class JumpFloorII {

    public int jumpFloorII(int target) {

        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else {
            return 2 * jumpFloorII(target - 1);
        }
    }


    public int jumpFloor2(int target) {
        return 1<<(target-1);
    }
}
