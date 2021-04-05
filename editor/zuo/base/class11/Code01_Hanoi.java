package base.class11;

/**
 * @author ：cwf
 * @description：汉诺塔问题
 * 体会递归的作用，明确递归方法的含义，和最终形态，不要去绕细节
 */
public class Code01_Hanoi {


    public static void main(String[] args) {

        hanoi(3);
    }

    private static void hanoi(int i) {

        process(i,"左","中","右");

    }

    //含义：把第i层的塔，从left 到 right
    private static void process(int i, String left, String help, String right) {
        if(i == 1){
            System.out.println("第 1 层 " + left +" - >" + right);
            return;
        }
        process(i-1,left,right,help);
        System.out.println("第 "+i+" 层 "+left + " - >" +right);
        process(i-1,help,left,right);
    }


}
