package base.class03;

import java.util.Arrays;

/**
 * @author ：cwf
 * @description：快排和荷兰国旗问题 荷兰国旗问题：把一个数组，按照数组中的一个数组，小于的放左边，等于的放中间，大于的放右边
 * <p>
 * <p>
 * 快排：一个数组，随机选择一个数，小于放左边，等于放中间，大于放右边
 * 然后对左边和右边进行同样的操作
 */
public class Code03_PartionAndQuickSort {


    //夹逼
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{1, 1};
        }
        int num = arr[R];
        int leftArea = L - 1;
        int rightArea = R + 1;
        int index = L;
        while (index < rightArea) {
            if (arr[index] < num) {
                swap(arr, index++, ++leftArea);
            } else if (arr[index] == num) {
                index++;
            } else {
                swap(arr, index, --rightArea);
            }
        }
        return new int[]{leftArea + 1, rightArea - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }


    //只需要单边夹逼
    public static int partition(int[] arr, int L, int R) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int num = arr[R];
        int leftArea = L -1;
        int index = L;
        while (index <=R){
            if(arr[index] <= num){
                swap(arr,++leftArea,index++);
            }else {
                index++;
            }
        }
        return leftArea;
    }

    public static void quickSort1(int[] arr){
        if(arr==null ||arr.length <2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    private static void process1(int[] arr, int L, int R) {
        if(L>=R){
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int partition = partition(arr, L, R);
        process1(arr,L,partition-1);
        process1(arr,partition+1,R);
    }


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
            int[] arr3 = copyArray(arr1);
            quickSort(arr1);
            Arrays.sort(arr2);
            quickSort1(arr3);
            if (!isEqual(arr1, arr2) && !isEqual(arr2,arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }


}
