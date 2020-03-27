package advance.util;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 测试代码工具类
 *
 * @author : LIUTAO
 * create at : 2020/3/26 下午12:02
 **/
public class TestUtil {

    public static List<User> getUserList(int number) {
        List<User> userList = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            User user = new User(10 + i);
            userList.add(user);
        }
        return userList;
    }

}
