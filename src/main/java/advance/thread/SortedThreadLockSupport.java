package advance.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * description: 线程通信demo，交替输出
 *
 * @author : LIUTAO
 * create at : 2020/3/26 下午2:02
 **/
public class SortedThreadLockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = new char[26];
        for (int i = 0; i < 26; i++) {
            int intI = i + 1;
            aI[i] = (char)intI;
        }

        char[] bI = new char[26];
        char ch = 'A';
        for (int i = 0; i < 26; i++) {
            bI[i] = ch;
            ch++;
        }

        lockSupport(aI, bI);

    }

    private static void lockSupport(char[] charArrA, char[] charArrB) {

        t1 = new Thread(() -> {
            aMethod(charArrA);
        }, "t1");

        t2 = new Thread(() -> {
            bMethod(charArrB);
        }, "t2");

        t1.start();
        t2.start();
    }

    private static void bMethod(char[] charArrB) {
        for (char c : charArrB) {
            LockSupport.park(t2);
            System.out.println(c);
            LockSupport.unpark(t1);
        }
    }

    private static void aMethod(char[] charArrA) {
        for (char c : charArrA) {
            System.out.println((int)c);
            LockSupport.unpark(t2); // 可以先unpark 再park
            LockSupport.park(t1);
        }
    }
}
