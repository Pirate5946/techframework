package advance.datastructureandalgorithm.listnode;

/**
 * description: 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * @author : LIUTAO
 * create at : 2020/4/17 下午12:38
 **/
public class Merge {



    /**
     * description:遍历解法
     *
     * @param [list1, list2]
     * @return advance.datastructureandalgorithm.listnode.ListNode
     * @author LIUTAO
     * @date 2020/4/17 下午12:40
     */
    public ListNode merge(ListNode list1, ListNode list2) {

        //新建一个头节点，用来存合并的链表。
        ListNode head = new ListNode(-1);
        head.next = null;
        ListNode root = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            } else {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }
        }
        //把未结束的链表连接到合并后的链表尾部
        if (list1 != null) {
            head.next = list1;
        }
        if (list2 != null) {
            head.next = list2;
        }
        return root.next;
    }

    /**
     * description:递归解法
     *
     * @author LIUTAO
     * @date 2020/4/17 下午12:40
     * @param [list1, list2]
     * @return advance.datastructureandalgorithm.listnode.ListNode
     */
    public ListNode recursionMerge(ListNode list1,ListNode list2) {
        // 先判空
        if (list1 == null) {
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = recursionMerge(list1.next, list2);
            return list1;
        } else {
            list2.next = recursionMerge(list1, list2.next);
            return list2;
        }
    }
}
