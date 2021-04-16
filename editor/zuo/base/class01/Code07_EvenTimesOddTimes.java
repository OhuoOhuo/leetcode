package base.class01;

/**
 * @author ：cwf
 * @date ：Created in 2021/3/10 14:49
 * @description：位与 运算
 * @modified By：
 * @version: $
 */
public class Code07_EvenTimesOddTimes {


    /**
     * 一个数组中 只有一个数出现奇数次找出该数
     */
    public static int printOddTimesNum1(int[] arr) {
        int i =0;
        for (int j = 0; j <arr.length ; j++) {
            i ^= arr[i];
        }
        return i;
    }

    /**
     * 一个数组中 有两种数出现奇数 找出这两种数；
     */

    public static void printOddTimesNum2(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length ; j++) {
            i ^= arr[i];
        }

        //i 为两个不相同的数进行了位与运算  a ^ b
        //j 为i 的最右的一个1
        int j = i & (~i+1);
        int one =0;
        //通最右是否为1 把整个数组区分成了两组
        for (int k = 0; k < arr.length ; k++) {
            if((arr[k] & j)==0){
                one ^= arr[k];
            }
        }
        System.out.println("其中一个:"+one +"另一个："+(one ^ j));
    }


}
