package base.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：cwf
 * @date ：Created in 2021/3/11 13:32
 * @description：双向链表实现队列和栈
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }


    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;


        public void addFromHead(T t) {
            Node<T> newNode = new Node<>(t);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.last = newNode;
                head = newNode;
            }
        }

        public void addFromBottom(T t) {
            Node<T> newNode = new Node<>(t);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public T popFromHead() {
            if (isEmpty()) {
                return null;
            }
            //需要更换头结点，所以需要记录头结点
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur.value;
        }

        public T popFromBottom() {
            if (isEmpty()) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }


    public static class MyStack<T> {
        public DoubleEndsQueue<T> doubleEndsQueue;

        public MyStack() {
            doubleEndsQueue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            doubleEndsQueue.addFromHead(value);
        }

        public T pop() {
            return doubleEndsQueue.popFromHead();
        }

        public Boolean isEmpty() {
            return doubleEndsQueue.isEmpty();
        }

    }

    public static class MyQueue<T> {
        DoubleEndsQueue<T> doubleEndsQueue;

        public MyQueue() {
            doubleEndsQueue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            doubleEndsQueue.addFromHead(value);
        }

        public T poll() {
            return doubleEndsQueue.popFromBottom();
        }

        public Boolean isEmpty() {
            return doubleEndsQueue.isEmpty();
        }

    }


    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
