package base.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：cwf
 * @description：两个队列实现栈 核心思想为准备两个队列，一个辅助；在获取时 其中一个队列中一个以外 的都倒入另一个队列，
 * 然后两者交互
 */
public class Code07_TwoQueueImplementStack {


    public class MyStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;


        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(Integer value) {
            queue.offer(value);
        }

        public Integer poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            Integer res = queue.poll();
            Queue temp = queue;
            queue = help;
            help = temp;
            return res;
        }

        public Integer peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            Integer res = queue.poll();
            help.offer(res);
            Queue temp = queue;
            queue = help;
            help = temp;
            return res;
        }
    }


}
