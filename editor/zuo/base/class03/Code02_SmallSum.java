package base.class03;

/**
 * @author ：cwf
 * @description：数组小和问题 * 一个数组中，一个数左边所有比它小的数的和，叫做这个数的小和，数组中所有数的小和累加起来，叫做数组小和
 * * ex：
 * * 【1,3,4,2,5】
 * * 1的小和 没有
 * * 3的小和 1
 * * 4的小和 1,3
 * * 2的小和 1
 * * 5的小和 1,3,4,2
 * * 数组小和：1+1+3+1+1+3+4+2
 */
public class Code02_SmallSum {

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    private static int smallSum(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int R = arr.length - 1;
        return process(arr, 0, R);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = (L + R) / 2;
        int left = process(arr, L, M);
        int right = process(arr, M + 1, R);
        int cur = merge(arr, L, M, R);
        return left + right + cur;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int sum = 0;
        int[] temp = new int[R - L + 1];
        int index = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            if (arr[p1] < arr[p2]) {
                //会产生小和
                temp[index] = arr[p1];
                //一次性批量压榨，这的arr[p1] < arr[2]  则该处p2之后的也大于p1 可以一次出来很多个！！！
                sum = sum + (R - p2 + 1) * arr[p1];
                index++;
                p1++;
            } else {
                temp[index++] = arr[p2++];
            }
        }

        while (p1 <= M) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[index++] = arr[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[i + L] = temp[i];
        }

        return sum;
    }

}
