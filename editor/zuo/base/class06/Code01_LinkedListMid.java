package base.class06;

import base.class02.Code01_ReverseList;

import java.util.ArrayList;

/**
 * @author ：cwf
 * @description：查找链表中间位置节点
 */
public class Code01_LinkedListMid {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        //test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

/*         ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");*/

    }

    private static Node midOrDownMidNode(Node head) {
        if(head == null || head.next ==null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next !=null && fast.next.next !=null){
            slow =slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static Node midOrUpMidNode(Node head) {
        //如果节点0-2个，直接返回头节点
        if(head == null || head.next ==null || head.next.next ==null){
            return head;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (fast.next !=null && fast.next.next !=null){
            slow= slow.next;
            fast =fast.next.next;
        }

        return slow;
    }


    private static Node right2(Node test) {
        if (test == null) {
            return null;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        Node cur = test;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        return nodes.get(nodes.size() / 2);

    }

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        return nodes.get((nodes.size() - 1) / 2);
    }



}
