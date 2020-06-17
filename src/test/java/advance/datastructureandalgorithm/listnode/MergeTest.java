package test.advance.datastructureandalgorithm.listnode; 

import advance.datastructureandalgorithm.listnode.ListNode;
import advance.datastructureandalgorithm.listnode.Merge;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Merge Tester. 
* 
* @author <Authors name> 
* @since <pre>4月 17, 2020</pre> 
* @version 1.0 
*/ 
public class MergeTest {

    Merge merge = null;

@Before
public void before() throws Exception {
    this.merge = new Merge();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: merge(ListNode list1, ListNode list2) 
* 
*/ 
@Test
public void testMerge() throws Exception { 
//TODO: Test goes here...
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node4 = new ListNode(4);
    node1.next = node2;
    node2.next = node4;
    node4.next = null;

    ListNode node3 = new ListNode(3);
    node3.next = null;

    ListNode merge = this.merge.merge(node1, node3);
    while (merge != null) {
        System.out.println(merge.val);
        merge = merge.next;
    }
}

/** 
* 
* Method: recursionMerge(ListNode list1, ListNode list2) 
* 
*/ 
@Test
public void testRecursionMerge() throws Exception { 
//TODO: Test goes here... 
} 


} 
