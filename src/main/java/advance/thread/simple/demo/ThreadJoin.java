package advance.thread.simple.demo;

/**
 * @author : LIUTAO
 * create at : 2019-11-29 15:37
 * @description: 测试join方法
 **/
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B线程任务开始");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B线程任务结束");
            }
        }){};

        t.start();
        System.out.println("A线程任务开始");
        // 模拟A线程任务执行了2秒
        Thread.sleep(2000);
        long waiteStart = System.currentTimeMillis();
        t.join();
        System.out.println("A线程等待B线程，等待了" + (System.currentTimeMillis() - waiteStart) / 1000 + "秒");
        System.out.println("A线程任务结束");
    }
}
