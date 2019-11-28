package advance.thread;

import cn.hutool.core.util.StrUtil;

import javax.sound.midi.Soundbank;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author : LIUTAO
 * create at : 2019-11-27 16:42
 * @description: 多线程简单例子
 **/
public class MultiThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(StrUtil.format("{}执行第{}次", threadName, i));
                }
            }
        });
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(StrUtil.format("{}线程启动", Thread.currentThread().getName()));
            }
        }).start();

        new Thread(){
            @Override
            public void run(){
                System.out.println(StrUtil.format("{}线程启动", this.getName()));
            }
        }.start();

        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 100; i++) {
            System.out.println(StrUtil.format("{}执行第{}次", threadName, i));
        }
    }


}
