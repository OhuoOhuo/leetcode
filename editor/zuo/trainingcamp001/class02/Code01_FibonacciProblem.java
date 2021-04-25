package trainingcamp001.class02;

/**
 * @author ：cwf
 * @date ：Created in 2021/4/25 09:58
 * @description： 1.斐波那契数 f: 1 1 2 3 5 8 13 21...... 后一个数等于前两个数的和，求第n个数
 * 2.上台阶：一个人可以一次往上迈1个台阶，也可以迈2个台阶  返回这个人迈上N级台阶的方法数
 * 3.生小牛：第一年农场有1只成熟的母牛A，往后的每年：
 * 1）每一只成熟的母牛都会生一只母牛
 * 2）每一只新出生的母牛都在出生的第三年成熟
 * 3）每一只母牛永远不会死
 * 返回N年后牛的数量
 */
public class Code01_FibonacciProblem {

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
//        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
//        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        /*         System.out.println(c3(n));*/
        System.out.println("===");

    }

    private static int c2(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 0;
        int leftOne = 3;
        int leftTwo = 2;
        int leftThree = 1;
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 4; i <= n; i++) {
            res = leftOne + leftThree;
            temp1 = leftOne;
            temp2 = leftTwo;
            leftOne = res;
            leftTwo = temp1;
            leftThree = temp2;
        }
        return res;
    }

    private static int c1(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    private static int s2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int res = 0;
        int leftTwo = 1;
        int leftOne = 2;
        for (int i = 3; i <= n; i++) {
            res = leftOne + leftTwo;
            leftTwo = leftOne;
            leftOne = res;
        }
        return res;
    }

    private static int s1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return s1(n - 1) + s1(n - 2);
    }

    private static int f2(int n) {
        if (n <= 0) {
            throw new RuntimeException("");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 0;
        int leftOne = 1;
        int leftTwo = 1;
        for (int i = 3; i <= n; i++) {
            res = leftOne + leftTwo;
            leftOne = leftTwo;
            leftTwo = res;
        }
        return res;
    }

    private static int f1(int n) {
        if (n <= 0) {
            throw new RuntimeException("数据不符要求");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }


}
