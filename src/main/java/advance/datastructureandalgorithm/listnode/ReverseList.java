package advance.datastructureandalgorithm.listnode;

/**
 * @author : LIUTAO
 * create at : 2019-09-15 11:14
 * @description: 反转列表
 * <link>https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking</link>
 **/
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode currentHead = head;
        while(currentHead != null) {
            ListNode next = currentHead.next;
            currentHead.next = newHead;
            newHead = currentHead;
            currentHead = next;
        }
        return newHead;
    }

    /**
     * description:递归解法
     *
     * @author LIUTAO
     * @date 2020/4/16 下午4:25
     * @param head
     * @return advance.datastructureandalgorithm.listnode.ListNode
     */
    public ListNode reverseListWithRecursive(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode preNode = reverseListWithRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return preNode;
    }
}
