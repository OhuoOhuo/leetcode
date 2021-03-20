package base.class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：cwf
 * @description：二叉树的序列化和反序列化 二叉树序列化，反序列化只有，前序，后续，层序是可以唯一还原的
 */
public class Code04_SerializeAndReconstructTree {


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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }

    private static Node buildByLevelQueue(Queue<String> levelQueue) {
        if(levelQueue ==null || levelQueue.size() <=0){
            return null;
        }
        String poll = levelQueue.poll();
        Node head = generateNode(poll);
        if(head ==null){
            return head;
        }

        LinkedList<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(head);
        Node node;
        while (!nodeQueue.isEmpty()){
            node = nodeQueue.poll();

            node.left = generateNode(levelQueue.poll());
            node.right = generateNode(levelQueue.poll());

            if(node.left !=null){
                nodeQueue.add(node.left);
            }
            if(node.right !=null){
                nodeQueue.add(node.right);
            }
        }

        return head;
    }


    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    private static Node buildByPosQueue(Queue<String> posQueue) {

        if (posQueue == null || posQueue.size() <= 0) {
            return null;
        }
        Stack<String> stack = new Stack<String>();
        while (!posQueue.isEmpty()) {
            stack.push(posQueue.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> posQueue) {
        String poll = posQueue.pop();

        if (poll == null) {
            return null;
        }
        Node node = new Node(Integer.valueOf(poll));
        node.right = posb(posQueue);
        node.left = posb(posQueue);

        return node;
    }


    private static Node buildByPreQueue(Queue<String> preQueue) {

        if (preQueue == null || preQueue.size() <= 0) {
            return null;
        }
        return preb(preQueue);
    }

    private static Node preb(Queue<String> preQueue) {
        String poll = preQueue.poll();
        if (poll == null) {
            return null;
        }
        Node node = new Node(Integer.valueOf(poll));
        node.left = preb(preQueue);
        node.right = preb(preQueue);
        return node;
    }

    private static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    ans.add(String.valueOf(poll.left.value));
                    queue.add(poll.left);
                } else {
                    ans.add(null);
                }
                if (poll.right != null) {
                    ans.add(String.valueOf(poll.right.value));
                    queue.add(poll.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    private static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        poss(queue, head);
        return queue;
    }

    private static void poss(Queue<String> queue, Node head) {
        if (head == null) {
            queue.add(null);
        } else {
            poss(queue, head.left);
            poss(queue, head.right);
            queue.add(String.valueOf(head.value));
        }
    }

    private static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(queue, head);
        return queue;
    }

    private static void pres(Queue<String> queue, Node head) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            pres(queue, head.left);
            pres(queue, head.right);
        }
    }
}
