package advance.thread.executor.executorservice;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author : LIUTAO
 * create at : 2019-11-21 17:20
 * @description: 测试定时线程
 **/
public class MyScheduledExecutorService {

    /**
     * 定义全局线程池
     */
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * 线程池接口： Executor 、ExecutorService、 ScheduledExecutorService
     * 线程池实现类： ThreadPoolExecutor 、 ScheduledThreadPoolExecutor
     * @param args
     */
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 5, 30, SECONDS,
                        new LinkedBlockingQueue<Runnable>(), threadFactory);

        beepForAWhile();
//        beepDelay(scheduler, 5, SECONDS);
    }

    public  void beepDelay(long delay, TimeUnit timeUnit) {
        Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };

        scheduler.schedule(beeper, delay, timeUnit);
        scheduler.shutdown();
    }

    public  static void beepForAWhile() {
        long startTime = System.currentTimeMillis();
        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
                System.out.println("秒钟计时：" + (System.currentTimeMillis() - startTime) / 1000);
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, SECONDS);

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                beeperHandle.cancel(true);
//                scheduler.shutdown();
                System.out.println("线程池空闲中。。。");
            }
        }, 10, SECONDS);
    }
}
