package base.class08;

import java.util.ArrayList;

/**
 * @author ：cwf
 * @description：求一颗树中最大搜索子树的头节点
 */
public class Code05_MaxSubBSTHead {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
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

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node node1 = maxSubBSTHead1(head);
            Node node2 = maxSubBSTHead2(head);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static Node maxSubBSTHead2(Node head) {

        if (head == null) {
            return null;
        }

        Info info = process(head);
        return info.maxHead;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        int max = head.value;
        int min = head.value;
        Node maxHead = head;
        int masSizeNum = 0;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxHead = leftInfo.maxHead;
            masSizeNum = leftInfo.masSizeNum;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.masSizeNum > masSizeNum) {
                maxHead = rightInfo.maxHead;
                masSizeNum = rightInfo.masSizeNum;
            }
        }

        if ((leftInfo == null ? true : (leftInfo.maxHead == head.left && leftInfo.max < head.value))
                && (rightInfo == null ? true : (rightInfo.maxHead == head.right && rightInfo.min > head.value))) {
            maxHead = head;
            masSizeNum = (leftInfo == null ? 0 : leftInfo.masSizeNum)
                    + (rightInfo == null ? 0 : rightInfo.masSizeNum) + 1;
        }
        return new Info(maxHead, masSizeNum, max,min);
    }

    public static class Info {
        public Node maxHead;
        public int masSizeNum;
        public int max;
        public int min;

        public Info(Node maxHead, int masSizeNum,  int max, int min) {
            this.maxHead = maxHead;
            this.masSizeNum = masSizeNum;
            this.max = max;
            this.min = min;
        }
    }


}
