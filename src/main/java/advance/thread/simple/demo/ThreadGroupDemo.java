package advance.thread.simple.demo;

/**
 * @ClassName ThreadGroupDemo
 * @Descrption TODO
 * @Author lt
 * @Date 2019/12/1 16:43
 * @Version 1.0
 **/
public class ThreadGroupDemo {
    public static void main(String[] args) {
        Thread t1=new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        ThreadGroup threadGroup = t1.getThreadGroup();
        System.out.println(threadGroup);
        ThreadGroup systemThreadGroup = threadGroup.getParent();
        System.out.println(systemThreadGroup);
        systemThreadGroup.list();//列出线程组树形结构，只会打印出已经start的线程信息
    }
}