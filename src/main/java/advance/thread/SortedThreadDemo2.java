package advance.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * description: 线程有序demo
 *
 * @author : LIUTAO
 * create at : 2020/4/14 上午4:48
 **/
public class SortedThreadDemo2 {
    static Thread a = null;
    static Thread b = null;
    static Thread c = null;

    public static void main(String[] args) {

        a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
                LockSupport.unpark(b);
            }
        });

        b = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park(b);
                System.out.println("B");
                LockSupport.unpark(c);
            }
        });

        c = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park(c);
                System.out.println("C");
            }
        });

        a.start();
        b.start();
        c.start();
    }
}
