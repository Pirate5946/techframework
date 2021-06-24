package advance.hutool;

import cn.hutool.core.util.StrUtil;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/13 下午4:43
 **/
public class StrUtilTest {
    public static void main(String[] args) {

        String test_test = "test_test";
        String testTest = StrUtil.toCamelCase(test_test);
        System.out.println(testTest);
    }
}
