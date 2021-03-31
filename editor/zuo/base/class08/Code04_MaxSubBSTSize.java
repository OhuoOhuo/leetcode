package base.class08;

import java.util.ArrayList;

/**
 * @author ：cwf
 * @description：求一颗树中最大搜索子树的节点数 是搜索子树 不是随便选节点拼凑成的满足搜索树的图
 */
public class Code04_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static int maxSubBSTSize2(Node head) {

        if (head == null) {
            return 0;
        }
        Info info = process(head);
        return info.maxBSTSize;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.value;
        int min = head.value;
        boolean isBST = false;
        int maxBSTSize = 1;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxBSTSize = Math.max(maxBSTSize, leftInfo.maxBSTSize);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
        }
        if ((leftInfo == null ? true : leftInfo.isBST)
                && (rightInfo == null ? true : rightInfo.isBST)
                && (leftInfo == null ? true : leftInfo.max < head.value)
                && (rightInfo == null ? true : head.value < rightInfo.min)
        ) {
            maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxBSTSize) + (rightInfo == null ? 0 : rightInfo.maxBSTSize) + 1;
            isBST = true;
        }
        return new Info(isBST, maxBSTSize, max, min);
    }

    public static class Info {
        public boolean isBST;
        public int maxBSTSize;
        public int max;
        public int min;

        public Info(boolean isBST, int maxBSTSize, int max, int min) {
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
        }
    }
}
