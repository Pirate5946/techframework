package advance.thread;

/**
 * description: 自旋实现线程有序
 *
 * @author : LIUTAO
 * create at : 2020/4/14 上午4:56
 **/
public class SortedThreadDemo3 {

    enum ThreadFlag{t1,t2, t3,}

    static volatile ThreadFlag threadFlag = ThreadFlag.t1;

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            sortedThreadDemo();
        }
    }

    private static void sortedThreadDemo() {
        Thread a = new Thread(() -> {
            System.out.print("A");
            threadFlag = ThreadFlag.t2;
        });

        Thread b = new Thread(() -> {
            while (threadFlag != ThreadFlag.t2) { }
            System.out.print("B");
            threadFlag = ThreadFlag.t3;
        });

        Thread c = new Thread(() -> {
            while (threadFlag != ThreadFlag.t3) {

            }
            System.out.println("C");
        });

        a.start();
        b.start();
        c.start();
    }
}
