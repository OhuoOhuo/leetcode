package trainingcamp001.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ：hyf
 * @date ：Created in 2021/4/23 11:25
 * @description 1.给定一个数组arr，则arr中的任意一个子数组，都一定可以计算出该子数组的累计和 和最小值，求该累计和*最小值的最大值。
 * 2.给定一个数组arr，其中每个数依次表示一个条形图的高度，相邻数间隔为1，求能划出的最大矩形面积。    （其实是同一个解法）
 */
public class Code04_AllTimesMinToMax {

    /**
     * 暴力方法：已每个数为头，找到它全部子数组，然后依次计算
     */
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    /**
     * 利用单调栈：
     *
     * 整体思路：以数组中每个数为最小值  依次考察
     * 得这个数，
     */
    public static int max2(int[] arr) {
        int size = arr.length;

        int[] sum = new int[size];
        sum[0] =arr[0];
        for (int i = 1; i < size ; i++) {
            sum[i] = sum[i-1]+arr[i];
        }

        int max =Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size ; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer pop = stack.pop();
                int oneMax = arr[pop] * (stack.isEmpty()?sum[i-1]:sum[i-1] - sum[stack.peek()]);
                max = Math.max(max,oneMax);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            int oneMax = arr[pop] * (stack.isEmpty()?sum[size-1]:sum[size-1] -sum[stack.peek()]);
            max = Math.max(max,oneMax);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            //int[] arr = gerenareRondomArray();
            int[] arr={2,1,3,4,7,5};
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
