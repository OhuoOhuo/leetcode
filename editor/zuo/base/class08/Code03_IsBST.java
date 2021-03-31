package base.class08;

import java.util.ArrayList;

/**
 * @author ：cwf
 * @description：判断是否是搜索二叉树 对于每个节点都有，左子树的最大值小于该节点，右子树的最小值大于该节点
 */
public class Code03_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        Info info = process(head);
        return info.isBST;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isBST = false;
        int max = 0;
        int min = 0;
        if (leftInfo == null && rightInfo == null) {
            isBST = true;
            max = head.value;
            min = head.value;
        } else if (leftInfo == null && rightInfo != null) {
            isBST = rightInfo.isBST && (head.value < rightInfo.min);
            max = Math.max(head.value, rightInfo.max);
            min = Math.min(head.value, rightInfo.min);
        } else if (leftInfo != null && rightInfo == null) {
            isBST = leftInfo.isBST && (leftInfo.max < head.value);
            max = Math.max(head.value, leftInfo.max);
            min = Math.min(head.value, leftInfo.min);
        } else {
            isBST = leftInfo.isBST && rightInfo.isBST && (leftInfo.max < head.value) && (head.value < rightInfo.min);
            max = Math.max(head.value, Math.max(leftInfo.max, rightInfo.max));
            min = Math.min(head.value, Math.min(leftInfo.min, rightInfo.min));
        }
        return new Info(isBST, max, min);
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
