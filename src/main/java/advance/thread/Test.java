package advance.thread;

/**
 * description: test
 *
 * @author : LIUTAO
 * create at : 2020/4/30 上午11:18
 **/
public class Test {

    static class InnerTask implements Runnable{

        @Override
        public void run() {
            System.out.println("inner task run!");
        }
    }

    public static void main(String[] args) {
        InnerTask innerTask = new InnerTask();

        innerTask.run();
        System.out.println("main method run !");
    }
}
