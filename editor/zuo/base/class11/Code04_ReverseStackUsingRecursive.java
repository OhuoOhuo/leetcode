package base.class11;

import java.util.Stack;

/**
 * @author ：cwf
 * @description：翻转栈，利用递归
 */
public class Code04_ReverseStackUsingRecursive {


    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

    //递归含义：翻转栈
    private static void reverse(Stack<Integer> stack) {
        if (stack.size() <= 1) {
            return;
        }
        //在不改变顺序的情况下 弹出最下面的值
        int lastNum = getLastNum(stack);
        //翻转栈中还剩下的数值
        reverse(stack);
        //把抽出来的数值给加回去
        stack.push(lastNum);
    }

    //在不改变其他顺序的情况下，弹出最下面的数值
    private static int getLastNum(Stack<Integer> stack) {

        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            int lastNum = getLastNum(stack);
            stack.push(pop);
            return lastNum;
        }
    }


}
