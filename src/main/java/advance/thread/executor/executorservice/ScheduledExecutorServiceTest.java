package advance.thread.executor.executorservice;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author : LIUTAO
 * create at : 2019-11-21 17:20
 * @description: 测试定时线程
 **/
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 30, SECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        beepForAnHour(scheduler);
        beepDelay(scheduler, 5, SECONDS);
    }

    public static void beepDelay(ScheduledExecutorService scheduler, long delay, TimeUnit timeUnit) {
        Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };
        scheduler.schedule(beeper, delay, timeUnit);
        scheduler.shutdown();
    }

    public static void beepForAnHour(ScheduledExecutorService scheduler) {
        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 5, 10, SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 10, SECONDS);
    }
}
