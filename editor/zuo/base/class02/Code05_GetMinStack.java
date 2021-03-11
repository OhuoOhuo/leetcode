package base.class02;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

/**
 * @author ：cwf
 * @description：对于栈，额外提供一个获取最小值的功能
 */
public class Code05_GetMinStack {

    private class MyMinStack {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;


        public void push(Integer value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            } else if (value <= getMin()) {
                minStack.push(value);
            }
            dataStack.push(value);
        }

        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            Integer pop = dataStack.pop();
            if (pop.equals(getMin())) {
                minStack.pop();
            }
            return pop;
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return minStack.peek();
        }
    }

}
