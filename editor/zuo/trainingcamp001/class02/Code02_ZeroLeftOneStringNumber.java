package trainingcamp001.class02;

/**
 * @author ：cwf
 * @date ：Created in 2021/4/25 10:53
 * @description：
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 * 返回有多少达标的字符串
 *
 * 动态规划
 */
public class Code02_ZeroLeftOneStringNumber {
    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            //System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }

    private static int getNum2(int n) {
        return 0;
    }

    private static int getNum1(int n) {
        if(n==1){
            return 1;
        }
        if(n ==2){
            return 2;
        }
        return 0;
    }

}
