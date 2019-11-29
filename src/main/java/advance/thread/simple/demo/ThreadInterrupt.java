package advance.thread.simple.demo;

/**
 * description: 线程中断demo
 *
 * @author : LIUTAO
 * create at : 2019-11-29 15:11
 **/
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run(){
                for (int i = 1; i < 100; i++) {
                    System.out.printf("%s运行第%d次%n", this.getName(), i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("收到中断信号，结束任务");
                        return;
                    }
                }
            }
        };
        t.start();

        //主线程休眠3秒
        Thread.sleep(3000);
        System.out.println("主线程休眠3秒后发出中断信号");
        t.interrupt();
    }
}
