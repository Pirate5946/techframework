package advance.datastructureandalgorithm.stackandqueue;/**
 * Created by lt on 2019/1/26.
 */

import java.util.Stack;

/**
 * @ClassName TwoStackQueue
 * @Descrption 用两个栈实现队列、支持队列的基本操作（add、poll、peek）
 * @Author lt
 * @Date 2019/1/26 15:01
 * @Version 1.0
 * @see <a href="https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。</a>
 **/
public class TwoStackQueue {
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.empty()) {
            stackPop.push(stackPush.pop());
        }
        return stackPop.peek();
    }
}
