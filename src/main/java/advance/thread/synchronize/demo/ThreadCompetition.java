package advance.thread.synchronize.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : LIUTAO
 * create at : 2019-11-22 16:39
 * @description: 线程竞争案例演示
 */
public class ThreadCompetition {

    /**
     * 使用两个线程，对于1个整形变量从各加五百万次。我们期望的结果是一千万，但结果是不是这样呢？
     */
    static int count = 0;
    static AtomicLong atomicLong = new AtomicLong(0);
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService executorService = new ThreadPoolExecutor(1,
                10,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                threadFactory);

        executorService.submit(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5000000; i++) {
                    count++;
                    atomicLong.getAndIncrement();

                }

//                                       Thread.currentThread().setName("线程一号");
                System.out.println(Thread.currentThread().getName() + ":计算完成...，耗时"
                        + (System.currentTimeMillis() - start));

            }
        });

        for (int i = 0; i < 5000000; i++) {
            count++;
            atomicLong.getAndIncrement();
        }

        System.out.println(Thread.currentThread().getName() + ":计算完成....，耗时" + (System.currentTimeMillis() - start));
        Thread.sleep(1000);
        System.out.println("count:" + count);
        System.out.println("atomicLong:" + atomicLong);
    }
}


