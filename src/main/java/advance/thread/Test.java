package advance.thread;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

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

    static final ExecutorService executor = ExecutorBuilder.create()
            .setCorePoolSize(10)
            .setMaxPoolSize(10)
            .setKeepAliveTime(1, TimeUnit.MINUTES)
            .setWorkQueue(new LinkedBlockingQueue<>(500))
            .setHandler(new ThreadPoolExecutor.CallerRunsPolicy())
            .build();

    private static void testInitDs() {
        Map<Integer, Integer> datasourceMap = new HashMap<>(8);
        List<Integer>         integers      = asList(1, 2, 3);

        integers.forEach(i -> {
            executor.execute(() -> {
                Integer dataSource = datasourceMap.get(i);
                if (dataSource == null) {
                    System.out.println(StrUtil.format("未查询到数据源:{},保存当前数据源"));

                    datasourceMap.put(i, dataSource);
                }
            });
            ThreadUtil.safeSleep(100);

        });
    }


    @org.junit.Test
    public void testGetThreadPoolState () {
        for (int i = 0; i < 500; i++) {
            executor.execute(() -> {
                System.out.print(1);
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) executor);
        int activeCount        = -1;
        while (activeCount != 0) {
            System.out.println();

            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数："+ queueSize);

            activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数："+ activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数："+ completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数："+ taskCount);

            ThreadUtil.sleep(2000);
        }
    }
}
