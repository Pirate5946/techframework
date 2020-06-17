package advance.datastructureandalgorithm.list;

import advance.util.TestUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import entity.User;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * description: 测试compareTo方法
 *
 * @author : LIUTAO
 * create at : 2020/3/26 上午11:57
 **/
public class CompareToTest {
    @Resource
    private TestUtil testUtil;

    private static final Log LOGGER = LogFactory.get();

    public static void main(String[] args) {
        List<User> userList = TestUtil.getUserList(4);
        compareTo(userList);
        String jsonStr = JSONUtil.toJsonStr(userList);
        System.out.println(jsonStr);
        LOGGER.info(jsonStr);
    }

    public static void compareTo(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //return o1.getAge().compareTo(o2.getAge());
                return o2.getAge().compareTo(o1.getAge());
            }
        });
    }
}
