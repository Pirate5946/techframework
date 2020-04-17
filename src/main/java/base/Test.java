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
        test1(test2());
    }

    public static void test1(int i) {
        System.out.println(i + 1);
    }

    public static int test2() {
        System.out.println(2);
        return 2;
    }


}
