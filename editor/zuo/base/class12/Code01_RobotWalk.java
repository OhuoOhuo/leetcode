package base.class12;

/**
 * @author ：cwf
 * @description：走路机器人 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 *
 */
public class Code01_RobotWalk {

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
/*        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways3(7, 4, 9, 5));
        System.out.println(ways4(7, 4, 9, 5));
        System.out.println(ways5(7, 4, 9, 5));*/
    }

    private static int ways1(int N, int M, int K, int P) {
        //判断异常情况
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return 0;
        }
        return process1(N, M, K, P);
    }

    /**
     * @param N    路长 不变
     * @param m    机器人挺的位置 改变
     * @param rest 剩下的步数 改变
     * @param P    最终位置 不变
     * @return
     */
    private static int process1(int N, int m, int rest, int P) {
        //base case 如果没有步数了，m落到P 则是一种方法 返回1 or 返回0
        if (rest == 0) {
            return m == P ? 1 : 0;
        }
        //当m 来到1时 只有往2 走，走的方法不增加
        if(m==1){
            return process1(N,2,rest-1,P);
        }
        //当 m ==n 时，只有网 N-1走，走的方法不增加
        if(m==N){
            return process1(N,N-1,rest-1,P);
        }
        return process1(N,m+1,rest-1,P)+process1(N,m-1,rest-1,P);
    }
}
