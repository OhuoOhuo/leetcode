package trainingcamp001.class01;

import java.util.LinkedList;

/**
 * @author ：hyf
 * @description：子数组小于问题 * 给定一个数组，和一个数；
 * * 它的子数组中的最大值 - 最小值<= 给定数 ，该子数组为有效数组，求有效数组的个数。
 * * <p>
 * * 暴力解法：求出所有子数组，再一一判所有子数组是否满足
 */
public class Code02_AllLessNumSubArray {

    private static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();

        int L = 0;
        int R = 0;

        int result = 0;
        //表示以L开头字数组所有的情况，把L的每种情况加起来即为要求情况
        while (L < arr.length) {
            while (R < arr.length) {

                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[R]) {
                    maxList.pollLast();
                }
                maxList.addLast(R);
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[R]) {
                    minList.pollLast();
                }
                minList.addLast(R);

                //如果R不满足，则R+1肯定不满足
                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //如果在窗口 L ---R内满足，则L+1 到R也一定满足，数据状况分析的常用方法
            result += (R - L);
            if (maxList.peekFirst() == L) {
                maxList.pollFirst();
            }
            if (minList.peekFirst() == L) {
                minList.pollFirst();
            }
            L++;
        }
        return result;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    //for test
    public static int getNum1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int L = 0;
        int R = 0;
        // [L..R) -> [0,0) 窗口内无数 [1,1)
        // [0,1) -> [0~0]
        int res = 0;
        while (L < arr.length) { // L是开头位置，尝试每一个开头

            // 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止

            while (R < arr.length) { // R是最后一个达标位置的再下一个
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                // R -> arr[R],
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                R++;
            }

            // R是最后一个达标位置的再下一个，第一个违规的位置
            res += (R - L);

            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }

            L++;

        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));
        System.out.println(getNum1(arr, num));

    }


}
