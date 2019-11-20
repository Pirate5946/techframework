package advance.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : LIUTAO
 * create at : 2019-11-20 10:07
 * @description: 测试OOM
 **/
public class OomTest {
    /**
     * description:
     *  1. 命令行调用程序，指定 Xmx，测试OOM，java -Xmx10m OomTest
     *  2. 先运行一遍main，然后edit configuration
     */
    public static void main(String[] args) {
        Map<Integer, Object> map = new HashMap<>(16);
        for (int i = 0; i < 10; i++) {
            map.put(i, new byte[1024 * 1024]);
        }
    }
}
