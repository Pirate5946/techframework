package advance.thread.executor.executorservice;

import cn.hutool.core.util.StrUtil;

import javax.swing.text.StringContent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName ExecutorExample
 * @Descrption 多个任务共享线程的Demo
 * @Author lt
 * @Date 2019/11/24 20:41
 * @Version 1.0
 **/
public class ExecutorExample {

    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void executorTask() {
        for (int i = 0; i < 10; i++) {
            executor.execute(new SimpleTask("task" + i));
        }
    }

    public static void main(String[] args) {
        ExecutorExample executorExample = new ExecutorExample();
        executorExample.setExecutor(Executors.newFixedThreadPool(3));
        executorExample.executorTask();
        System.out.println("线程池空闲中。。。");
    }

    class SimpleTask implements Runnable {

        private String taskName;

        public SimpleTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(StrUtil.format("do {} ... in Thread {}", taskName, Thread.currentThread().getId()));
        }
    }
}
