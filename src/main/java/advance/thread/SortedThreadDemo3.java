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

    static volatile int flag = 1;

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            sortedThreadDemo();
        }
    }

    private static void sortedThreadDemo() {
        Thread a = new Thread(() -> {
            while (flag != 1) { }
            System.out.print("A");
//            threadFlag = ThreadFlag.t2;
            flag = 2;
        });

        Thread b = new Thread(() -> {
            while (flag != 2) { }
            System.out.print("B");
            flag = 3;
        });

        Thread c = new Thread(() -> {
            while (flag != 3) {

            }
            System.out.println("C");
            flag = 1;
        });

        a.start();
        b.start();
        c.start();
    }
}
