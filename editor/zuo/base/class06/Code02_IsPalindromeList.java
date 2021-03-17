package base.class06;

import java.util.Stack;

/**
 * @author ：cwf
 * @description：链表是否是回文
 */
public class Code02_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }

    /**
     * 只用有限几个变量
     * 找到中点（奇数中点，偶数上中点）
     * 然后把后面的链表反转
     * 再依次比较
     */
    private static boolean isPalindrome3(Node head) {

        if(head==null || head.next ==null){
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //slow 为中点 or 上中点
        //翻转slow 后续节点
        Node pre = slow.next;
        slow.next =null;
        Node temp = null;
        while (pre!=null){
            temp = pre.next;
            pre.next = slow;
            slow = pre;
            pre = temp;
        }
        //slow 为右侧头节点

        while (head !=null && slow!=null){
            if(head.value !=slow.value){
                return false;
            }

            head = head.next;
            slow = slow.next;
        }
        //todo 理论上还需要把链表还原
        return true;
    }

    /**
     * 利用快慢指针，减少栈空间
     */
    private static boolean isPalindrome2(Node head) {

        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> nodes = new Stack<>();
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        while (slow !=null){
            nodes.push(slow);
            slow = slow.next;
        }
        while (!nodes.isEmpty()){
            if( nodes.pop().value !=head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 利用栈
     */
    private static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node cur = head;
        Stack<Node> nodes = new Stack<>();

        while (cur != null) {
            nodes.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (nodes.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }


}
