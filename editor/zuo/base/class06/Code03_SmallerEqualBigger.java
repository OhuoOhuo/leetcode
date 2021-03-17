package base.class06;

/**
 * @author ：cwf
 * @description：把链表划分为小于等于大于
 */
public class Code03_SmallerEqualBigger {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

    //转换为数组，然后做partition，在转换为链表
    private static Node listPartition1(Node head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[size];
        int i = 0;
        cur = head;
        while (cur != null) {
            nodeArr[i] = cur;
            i++;
            cur = cur.next;
        }
        arrPartition(nodeArr, num);
        head = nodeArr[0];
        Node pre = head;
        for (int j = 1; j < nodeArr.length; j++) {
            pre.next = nodeArr[j];
            pre = pre.next;
        }
        pre.next = null;
        return head;
    }

    private static void arrPartition(Node[] nodeArr, int num) {
        int left = -1;
        int right = nodeArr.length;
        int i = 0;
        while (i < right) {
            if (nodeArr[i].value < num) {
                swap(nodeArr, i++, ++left);
            } else if (nodeArr[i].value == num) {
                i++;
            } else if (nodeArr[i].value > num) {
                swap(nodeArr, i, --right);
            }
        }
    }

    private static void swap(Node[] nodeArr, int i, int left) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[left];
        nodeArr[left] = temp;
    }

    //拆分成3条链表，再拼接一起
    private static Node listPartition2(Node head, int num) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;

        Node temp = null;
        while (head != null) {
            temp = head.next;
            head.next = null;
            if (head.value < num) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == num) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else if (head.value > num) {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = temp;
        }
        //拼装3条链表 8中情况
        if(sH==null && eH==null && mH ==null){
            return null;
        }
        if(sH==null && eH==null && mH !=null){
            head = mH;
        }
        if(sH==null && eH!=null){
            head = eH;
            eT.next = mH;
        }
        if(sH!=null && eH ==null ){
            head = sH;
            sT = mH;
        }
        if(sH!=null && eH !=null){
            head =sH;
            sT.next = eH;
            eT.next = mH;
        }
        return head;
    }
}
