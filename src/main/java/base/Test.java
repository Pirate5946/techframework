package base;

import entity.Employee;
import cn.hutool.json.JSONUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName Test
 * @Descrption TODO
 * @Author lt
 * @Date 2019/11/10 0:06
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        final int COUNT_BITS = Integer.SIZE - 3;
        int running = -1 << COUNT_BITS;
        System.out.println(running);
        System.out.println(running );

        System.out.println(getBinaryFromInt(2));
        System.out.println(getBinaryFromInt(4));
        System.out.println(getBinaryFromInt(running));
        System.out.println(getBinaryFromInt(0 << COUNT_BITS));
        System.out.println(getBinaryFromInt(1 << COUNT_BITS));
        System.out.println(getBinaryFromInt(2 << COUNT_BITS));
        System.out.println(getBinaryFromInt(3 << COUNT_BITS));

    }

    public static String getBinaryFromInt(int i) {
        StringBuilder string = new StringBuilder();
        while (i != 0) {
            int temp = i % 2;
            i /= 2;
            string.insert(0, temp);
        }
        return string.toString();
    }



    public static void test1(int i) {
        System.out.println(i + 1);
    }

    public static int test2() {
        System.out.println(2);
        return 2;
    }


}
