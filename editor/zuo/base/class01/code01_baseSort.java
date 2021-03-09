package base.class01;

import java.util.Arrays;

/**
 * @author ：cwf
 * @description：简单的复杂度为n^2的排序
 */
public class code01_baseSort {

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() ->  [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机 
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
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
        int maxSize = 100; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                printArray(arr1);
                // 打印arr2
                printArray(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

/*        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        insertionSort(arr);
        printArray(arr);*/
    }
    //插入排序
    private static void insertionSort(int[] arr1) {
        if(arr1 ==null || arr1.length <2){
            return;
        }
        for (int i = 1; i < arr1.length ; i++) {
            for (int j = 0; j < i ; j++) {
                if(arr1[i] < arr1[j]){
                    swap(arr1,i,j);
                }
            }
        }
    }
    
    //选择排序
    private static void selectionSort(int[] arr2){
        if(arr2 ==null || arr2.length<2){
            return;
        }
        for (int i = 0; i < arr2.length-1 ; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr2.length ; j++) {
                if(arr2[j] <arr2[minIndex] ){
                    minIndex = j;
                }
            }
            swap(arr2,minIndex,i);
        }
    }
    
    //冒泡排序
    private static void bubbleSort(int[] arr3){
        if(arr3 ==null || arr3.length <2){
            return;
        }
        for (int i = arr3.length -1; i >= 0 ; i--) {
            for (int j = 0; j < i ; j++) {
             if(arr3[i] < arr3[j]){
                 swap(arr3,i,j);
             }
            }
        }
    }

    private static void swap(int[] arr3, int i, int j) {
        int temp = arr3[i];
        arr3[i] = arr3[j];
        arr3[j] = temp;
    }


}
