package advance.datastructureandalgorithm.stackandqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/4/30 下午4:35
 **/
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<Integer>();
        // 插入  add  offer  put
        linkedBlockingQueue.add(1);
        linkedBlockingQueue.add(2);


        // 取出 remove poll take
        // head 为null， head.next() 为第一个有值的Node
        Integer remove = linkedBlockingQueue.poll();
        System.out.println(remove);
    }
}
