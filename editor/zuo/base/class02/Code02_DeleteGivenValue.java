package base.class02;

/**
 * @author ：cwf
 * @description：删除给定数值的节点
 */
public class Code02_DeleteGivenValue {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node removeValue(Node head, int num) {

        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        //head来到第一个不相等的位置

        Node cur = head;
        Node last = head;
        //双指针，last记录后面节点位置
        while (cur != null) {
            //当需要删除时，后面节点next 指向前面节点 next
            if (cur.value == num) {
                last.next = cur.next;
                //当不需要删除时，后面节点追上前面节点
            } else {
                last = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
