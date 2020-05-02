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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CheckedOutputStream;

/**
 * @ClassName Test
 * @Descrption TODO
 * @Author lt
 * @Date 2019/11/10 0:06
 * @Version 1.0
 **/
public class Test {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public void testCtl() {
        int c = ctl.get();
        System.out.println("c:"+ getBinaryFromInt(c));
        System.out.println();
        int workerCountOf = workerCountOf(c);
        System.out.println("workerCountOf :" + workerCountOf);

        int runStateOf = runStateOf(c);
        System.out.println("runStateOf:" + runStateOf);
        System.out.println("runStateOf1:" + runStateOf(1));

    }

    public static void main(String[] args) {


        System.out.println("CAPACITY:" + getBinaryFromInt(CAPACITY));
        System.out.println("~CAPACITY:" + getBinaryFromInt(~CAPACITY));
        System.out.println("RUNNING:" + getBinaryFromInt(RUNNING));
        System.out.println("TERMINATED:" + getBinaryFromInt(TERMINATED));
        System.out.println("SHUTDOWN:" + getBinaryFromInt(SHUTDOWN));

        System.out.println(0 < SHUTDOWN);

    }

    public static String getBinaryFromInt(int i) {
        StringBuilder string = new StringBuilder();
        if (i == 0) {
            return "0";
        }
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
