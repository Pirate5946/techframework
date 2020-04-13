package advance.datastructureandalgorithm.listnode;

import advance.datastructureandalgorithm.listnode.ListNode;

import java.util.ArrayList;

/**
 * description: 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * @author : LIUTAO
 * create at : 2020/4/12 下午9:50
 *
 * 三种思路：
 * *    1. 栈 ， 链表节点入栈，然后出栈
 * *    2. 递归， 方法栈（list需要在递归方法外面声明）
 * *    3. 顺序存入list，然后倒序放入另一个list
 **/
public class PrintListFromTailToHead {
    ArrayList<Integer> arrayList=new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            this.printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}
