package base.class12;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：cwf
 * @description：换零钱 给定一个正数数组，数组里面的数不重复
 * 在给定一个目标数，求用数组里面的数拼凑成该目标数的方法数（数组里面的数可以多次使用）
 */
public class Code09_CoinsWay {

    public static void main(String[] args) {
        int[] arr = {5, 10, 50, 100};
        int sum = 1000;
        System.out.println(ways1(arr, sum));
/*        System.out.println(ways2(arr, sum));
        System.out.println(ways3(arr, sum));
        System.out.println(ways4(arr, sum));*/
    }

    private static int ways1(int[] arr, int sum) {
        if (sum <= 0 || arr == null || arr.length <= 0) {
            return 0;
        }
        return process(arr, sum, 0);
    }

    /**
     * @param arr
     * @param rest
     * @return
     */
    private static int process(int[] arr, int rest, int i) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways =0;
        for (int zhang = 0; zhang  * arr[i] <=rest ; zhang++) {
            ways += process(arr,rest - zhang* arr[i],i+1);
        }
        return ways;
    }
}
