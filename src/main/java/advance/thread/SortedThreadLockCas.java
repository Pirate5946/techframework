package advance.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * description: 线程通信demo，交替输出 ,
 *  <b>采用CAS思想，自旋锁空转</b>
 *
 * @author : LIUTAO
 * create at : 2020/3/26 下午2:02
 **/
public class SortedThreadLockCas {

    enum ReadyToRun{T1, T2,}

    static volatile ReadyToRun r = ReadyToRun.T1;

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

        casLock(aI, bI);

    }

    private static void casLock(char[] charArrA, char[] charArrB) {



        t1 = new Thread(() -> {
            for (char c : charArrA) {
                while (r != ReadyToRun.T1) {

                }
                System.out.println((int)c);

                r = ReadyToRun.T2;
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : charArrB) {
                while (r != ReadyToRun.T2) {

                }
                System.out.println(c);

                r = ReadyToRun.T1;
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
