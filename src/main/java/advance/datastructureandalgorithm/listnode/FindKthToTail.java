package advance.datastructureandalgorithm.listnode;

/**
 * description: 输入一个链表，输出该链表中倒数第k个结点。
 *
 * @author : LIUTAO
 * create at : 2020/4/16 下午3:32
 **/
public class FindKthToTail {

    public ListNode findKthToTail(ListNode head,int k) {

        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k) {
                q = q.next;
            }
            p = p.next;
        }
        return i < k ? null : q;
    }

    public ListNode findKthToTail2(ListNode head,int k) {

        if (head == null) {
            return null;
        }
        int count = 1;
        ListNode next = head.next;
        while (next != null) {
            count++;
            next = next.next;
        }
        if (count < k) {
            return null;
        }
        ListNode kNode = head;
        for (int i = 0; i < count - k; i++) {
            kNode = kNode.next;
        }
        return kNode;
    }


}
