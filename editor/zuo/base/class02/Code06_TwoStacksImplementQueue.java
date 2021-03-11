package base.class02;

import java.util.Stack;

/**
 * @author ：cwf
 * @description：两个栈实现队列 核心思想为，分为push 和 pop两个栈，当pop栈为空时，把push栈中的东西倒入pop中
 * 可以在任何操作中都倒一次
 */
public class Code06_TwoStacksImplementQueue {

    public class MyQueue {
        Stack<Integer> pushStack;
        Stack<Integer> popStack;

        public MyQueue() {
            popStack = new Stack<>();
            pushStack = new Stack<>();
        }

        //如果pop栈中为空了，把push栈中倒入pop中
        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void push(int value) {
            pushStack.push(value);
            pushToPop();
        }

        public Integer pop() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                new RuntimeException("");
            }
            pushToPop();
            return popStack.pop();
        }

        public Integer pee() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                new RuntimeException("");
            }
            pushToPop();
            return popStack.peek();
        }

    }


}
