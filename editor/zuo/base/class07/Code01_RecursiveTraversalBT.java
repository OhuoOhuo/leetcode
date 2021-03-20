package base.class07;

/**
 * @author ：hyf
 * @description：递归遍历二叉树 * 前序，中序，后序
 * * 其本质为遍历会到每个节点3次 参考方法f
 * * 前序遍历 为第一次到达打印
 * * 中序遍历 为第二次到达打印
 * * 后序遍历 为第三次到达打印
 * <p>
 * 可以用一个节点来举例
 */
public class Code01_RecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void f(Node head) {
        if (head == null) {
            return;
        }

        // 此处打印前序
        //System.out.print(head.value + " ");
        f(head.left);
        System.out.print(head.value + " ");
        // 此处打印中序
        f(head.right);
        //System.out.print(head.value + " ");
        // 此处打印后续
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
/*        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.left.right.right = new Node(11);
        head.right.left.left = new Node(12);
        head.right.left.right = new Node(13);
        head.right.right.left =new Node(14);
        head.right.right.right =new Node (15);*/


        System.out.println("整体遍历");
        f(head);

    }


}
