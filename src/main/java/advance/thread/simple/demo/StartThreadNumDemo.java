package advance.thread.simple.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * <ul>
 * <li>4-Signal Dispatcher //分发处理发送给JVM信号的线程</li>
 *
 * <li>3-Finalizer //调用对象的finalize方法的线程，就是垃圾回收的线程</li>
 *
 * <li>2-Reference Handler //清除reference的线程</li>
 *
 * <li>1-main //主线程</li>
 * </ul>
 *
 * @author : LIUTAO
 * create at : 2019-11-27 17:04
 * @description: java程序启动至少会启动几个线程
 **/
public class StartThreadNumDemo {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos=threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId()+"-"+threadInfo.getThreadName());
        }
    }
}