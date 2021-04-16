package base.class11;

/**
 * @author ：cwf
 * @description：背包问题
 * 给定两个数组，表示物体的重量，一个表示物体价值，给定一个容量有限的背包，求能背的最大重量
 *
 */
public class Code07_Knapsack {


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }

    private static int dpWay(int[] weights, int[] values, int bag) {
        if(bag <=0||weights==null || weights.length<0){
            return 0;
        }

        int N = weights.length;
        int M = bag;
        int[][] dp= new int[N+1][M+1];

        for (int i = N -1; i >=0 ; i--) {
            for (int rest = 0; rest <= bag ; rest++) {

                int p1 = dp[i+1][rest];
                int p2 =-1;
                if(rest >= weights[i]){
                    int pro = dp[i+1][rest-weights[i]];
                    p2 = pro+values[i];
                }
                dp[i][rest] = Math.max(p1,p2);
            }
        }


        return dp[0][bag];
    }

    private static int maxValue(int[] weights, int[] values, int bag) {
        if(bag <=0||weights==null || weights.length<0){
            return 0;
        }

        return process(0,weights,values,bag);
    }

    /**
     * 含义：从i 到 weights.length 能够返回的价值
     * @param i 从i开始考察
     * @param weights
     * @param values
     * @param reset  剩下的背包重量
     * @return
     */
    private static int process(int i, int[] weights, int[] values, int reset) {
        if(i == weights.length){//base case 超过时返回0
            return 0;
        }
        //i不加入背包
        int p1 = process(i + 1, weights, values, reset);
        //i加入背包
        int p2 =-1;
        if(weights[i] <=reset){
            int process2 = process(i+1,weights,values,reset - weights[i]);
            p2 = process2+values[i];
        }
        return Math.max(p1,p2);
    }
}
