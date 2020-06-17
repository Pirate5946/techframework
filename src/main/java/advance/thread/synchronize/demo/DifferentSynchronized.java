package advance.thread.synchronize.demo;

/**
 * @author : LIUTAO
 * create at : 2019-12-02 15:22
 * @description: 不同的synchronized 例子
 **/
public class DifferentSynchronized {

    public static void main(String[] args){
        Counter counter = new Counter();
        // 在有多个实例的情况下，如果我们依然要实现之前的结果，可以将Counter的代码改一下,将add方法用 static修饰
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
    }

    // 计数器
    static class Counter{
        long count = 0;

        public synchronized void add(long value){
            this.count += value;
            System. out.println(Thread. currentThread().getName()+":"+ count);
        }
    }
    //计数线程
    static class CounterThread extends Thread{

        protected Counter counter = null;

        public CounterThread(Counter counter){
            this.counter = counter;
        }

        @Override
        public void run() {
            for(int i=0; i<10; i++){
                counter.add(1);
            }
        }
    }
}
