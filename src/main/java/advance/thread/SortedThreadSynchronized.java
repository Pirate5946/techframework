package advance.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * description: 线程通信demo，交替输出
 *
 * @author : LIUTAO
 * create at : 2020/3/26 下午2:02
 **/
public class SortedThreadSynchronized {
    static Thread t1 = null, t2 = null;

    private static volatile boolean t2Started = false;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

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

        final Object o = null;

        t1 = new Thread(() -> {

            //countDownLatch.await();

            synchronized (o) {
                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : charArrA) {
                    System.out.println((int)c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t1");

        t2 = new Thread(() -> {
            synchronized (o) {
                for (char c : charArrB) {
                    System.out.println((int)c);
                    //countDownLatch.countDown();
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
