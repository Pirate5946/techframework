package advance.thread.reentrantlock;

import cn.hutool.core.util.StrUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/5/2 上午12:54
 **/
public class ReentrantLockTest {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("current Thread : "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                        lock.lock();
                        getThreadNameAndState();
//                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }, "t1").start();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    getThreadNameAndState();
                    try {
                        lock.lock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            executorService.shutdown();


            System.out.println("sleep ...");
            Thread.sleep(3000);
//            LockSupport.parkNanos(1000 * 10000);

//            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void getThreadNameAndState() {
        System.out.println(StrUtil.format("current Thread :{} , state:{} ", Thread.currentThread().getName(), Thread.currentThread().getState()));
    }
}
