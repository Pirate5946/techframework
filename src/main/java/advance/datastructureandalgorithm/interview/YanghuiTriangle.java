package advance.datastructureandalgorithm.interview;

/**
 * description: 杨辉三角
 *
 * @author : LIUTAO
 * create at : 2020/3/27 下午8:21
 **/
public class YanghuiTriangle {
    public static void main(String[] args) {

    }

    public static Integer getNum(int row, int col) {
        int number[][] = new int[row][col];

        if (row < 0 || col < 1 || number == null || number.length == 0) {
            return null;
        }

        for (int i = 0; i < number.length; i++) {
            number[i] = new int[i + 1];             //初始化第二层数组的大小
            for (int j = 0; j <= i; j++) {
                if (i == 0 || j == 0 || j == i) {         //将两侧的数组元素赋值为1
                    number[i][j]=1;
                } else {                         //其他数值根据公式计算
                    number[i][j] = number[i - 1][j] + number[i - 1][j - 1];
                }
            }
        }
        return number[row][col];
    }
}
