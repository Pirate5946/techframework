package advance.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/5/1 下午2:19
 **/
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(0, 10
                , 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());

        myPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        });

    }
}
