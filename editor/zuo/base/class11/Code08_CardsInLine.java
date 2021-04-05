package base.class11;

/**
 * @author ：cwf
 * @description：抽牌问题
 * 给定一个数组模拟一副牌，只可以从两边抽牌，所有牌可见
 * 一个人先手，一个人后手，假如两个人都绝顶聪明（明牌，先后手决定，胜负就已经决定了），两个人抽取到的牌的和的最大值。
 */
public class Code08_CardsInLine {

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5, 19, 29, 80, 4};
        System.out.println(getMax(arr));

     //   System.out.println(dpWay(arr));

    }

    private static int getMax(int[] arr) {

        return Math.max(early(arr,0,arr.length-1),later(arr,0,arr.length-1));
    }

    private static int later(int[] arr, int L, int R) {
        if(L ==R){
            return 0;
        }else {
            return Math.min(early(arr,L+1,R),early(arr,L,R-1));
        }
    }

    private static int early(int[] arr, int L, int R) {
        if(L ==R){
            return arr[R];
        }else {
            int p1 = arr[L] +later(arr,L+1,R);
            int p2 = arr[R] +later(arr,L,R-1);
            return Math.max(p1,p2);
        }
    }
}
