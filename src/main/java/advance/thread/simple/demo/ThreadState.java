package advance.thread.simple.demo;

import java.util.concurrent.TimeUnit;

/**
 * @Descrption http://www.tianshouzhi.com/api/tutorials/mutithread/107
 *
 * @ClassName ThreadState
 * @Author lt
 * @Date 2019/12/1 15:59
 * @Version 1.0
 **/
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {

        Thread runningThread = new Thread(new Running(), "RunningThread");
        runningThread.start();

        Thread timeWaitingThread = new Thread(new TimeWaiting(), "TimeWaitingThread");
        timeWaitingThread.start();

        Thread waitingThread1 = new Thread(new Waiting(), "WaitingThread-1");
        waitingThread1.start();

        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        Thread blockThread1 = new Thread(new Blocked(), "BlockedThread-1");
        Thread blockThread2 = new Thread(new Blocked(), "BlockedThread-2");
        blockThread1.start();
        blockThread2.start();

        Thread.sleep(1000L);

        System.out.println(runningThread.getName() + ":" + runningThread.getState());
        System.out.println(timeWaitingThread.getName() + ":" +timeWaitingThread.getState());
        System.out.println(waitingThread1.getName() + ":" + waitingThread1.getState());
        System.out.println(blockThread1.getName() + ":" + blockThread1.getState());
        System.out.println(blockThread2.getName() + ":" + blockThread2.getState());
    }

    /**
     * 表示线程正在运行
     * @author TIANSHOUZHI336
     *
     */
    static class Running implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getState());
            for (int i = 0; i < Long.MAX_VALUE; i++) {
                i++;
            }
        }

    }
    /**
     * 该线程不断的进行睡眠
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class实例上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    public static class SleepUtils {
        static void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
            }
        }
    }
}
