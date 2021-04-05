package base.class11;

/**
 * @author ：cwf
 * @description：数字字符串有多少种转换方式 *
 * * 给定一个数值串
 * * 1-a 2-b 3-c ......26-z对应
 * * 求有多少种转换方式
 * *
 */
public class Code06_ConvertToLetterString {

    public static void main(String[] args) {
        System.out.println(number("11111"));
        //System.out.println(dpWay("11231101"));
    }

    private static int number(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int process = process(0, chars);
        return process;
    }

    //从i位置开头，到chars.length 有多少多少种方法
    private static int process(int i, char[] chars) {
        if (i == chars.length) {//base case 超出范围至少有一种
            return 1;
        }
        //当以0开头的时候，不能转换
        if (chars[i] == '0') {
            return 0;
        }
        //当以1 开头
        if (chars[i] == '1') {
            //i+1 的方法次数
            int res = process(i + 1, chars);
            //如果 i+2 没有大于chars.length
            //i可以和i+1任何数字结合，所以也有i+2的方法次数
            if(i+1 <chars.length){
                res += process(i+2,chars);
            }
            return res;
        }
        //当以2 开头
        if (chars[i] == '2') {
            //i+1 次数是必须有的
            int res = process(i+1,chars);
            //i+2 的有一定条件,i+1 的值不大于 6
            if(i+1 <chars.length && chars[i+1]<='6'){
                res += process(i+2,chars);
            }
            return res;
        }
        //考察的i >=3 则不会增加方法可能性
        return process(i + 1, chars);
    }
}
