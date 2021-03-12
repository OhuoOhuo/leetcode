package base.class03;

import java.util.Arrays;

/**
 * @author ：cwf
 * @description：归并排序
 */
public class Code01_MergeSort {


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            Arrays.sort(arr2);
            //System.out.println("排序后");
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

    private static void mergeSort(int[] arr1) {

        if (arr1 == null || arr1.length < 2) {
            return;
        }
        int R = arr1.length - 1;
        procss(arr1, 0, R);
    }


    private static void procss(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = (R + L) / 2;
        procss(arr, L, M);
        procss(arr, M + 1, R);
        merge(arr, L, M, R);
    }


    //外排：把两个有序数组排成一个有序数组
    private static void merge(int[] arr, int L, int M, int R) {
        int[] temp = new int[R - L +1];
        int newIndex = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            if (arr[p1] <= arr[p2]) {
                temp[newIndex++] = arr[p1++];
            } else {
                temp[newIndex++] = arr[p2++];
            }
        }
        while (p1 <= M) {
            temp[newIndex++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[newIndex++] = arr[p2++];
        }
        p1 = L;
        for (int i = 0; i < temp.length; i++) {
            arr[p1++] = temp[i];
        }
    }


    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;// 当前有序的，左组长度
        while (mergeSize < N) { // log N
            int L = 0;
            // 0....
            while (L < N) {
                // L...M  左组（mergeSize）
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                //  L...M   M+1...R(mergeSize)
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }


}
