package advance.thread.callable;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

/**
 * description: FutureTaskDemo
 *
 * @author : LIUTAO
 * create at : 2020/4/15 上午12:23
 **/
public class FutureTaskDemo implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        FutureTask<Integer> futureTask = new FutureTask<>(new FutureTaskDemo());
        executor.submit(futureTask);
        try {
            System.out.println(futureTask.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
