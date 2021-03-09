package base.class01;

import java.util.Arrays;

/**
 * @author ：cwf
 * @description：二分查找-有序数组是否包含该数
 */
public class code02_BSExist {


    // for test
    public static boolean test(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    private static boolean exist(int[] arr, int value) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L <= R) {
            mid = (L + R) / 2;

            if (arr[mid] == value) {
                return true;
            } else if (arr[mid] < value) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

}
