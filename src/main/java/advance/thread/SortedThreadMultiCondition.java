package advance.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 线程通信demo，交替输出
 *
 * @author : LIUTAO
 * create at : 2020/3/26 下午2:02
 **/
public class SortedThreadMultiCondition {
    static Thread t1 = null, t2 = null;

    private static volatile boolean t2Started = false;


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
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition(); // 可以理解为队列
        Condition condition2 = lock.newCondition();

        final Object o = null;

        t1 = new Thread(() -> {

            try {
                lock.lock();

                for (char c : charArrA) {
                    System.out.println((int)c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        t2 = new Thread(() -> {
            try {
                lock.lock();

                for (char c : charArrB) {
                    System.out.println(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
