/**
 * 图方块问题，给的一个字符串由R（红）G（绿）构成，可以随意涂改RG，问至少涂几次，可以是所有R 都在 G 左边
 * 解题大思路：从左到右，如果该下标即以前全是R 需要涂改几个，即i之前的g + i之后的r
 * 需要循环一遍，记录某个位置累计的r 和 g
 */
public class Code04_ColorLeftRight {

     // RGRGR -> RRRGG
    public static int colorLeftRight(String str){
        if(str ==null ||str.length()<2){
            return 0;
        }

        char[] chars = str.toCharArray();
        int length = chars.length;

        //重前到后每个位置累计有几个g
        int[] left = new int[length];
        for (int i = 0; i < length; i++) {
            if(chars[i]=='G'){
                left[i] = (i==0?1:(left[i-1]+1));
            }else {
                left[i] = (i==0?0:left[i-1]);
            }
        }
        //重后到前 每个位置累计有几个r
        int[] right = new int[length];
        for (int i = length-1; i >=0 ; i--) {
            if(chars[i]=='R'){
                right[i] = (i==length-1?1:(right[i+1] +1));
            }else {
                right[i] = i==length-1?0:(right[i+1]);
            }
        }

        int res = Math.min(right[0],left[length-1]);

        for (int i = 1; i < length-1 ; i++) {

            if(chars[i]=='R'){
                Math.min(left[i]+right[i+1],res);
            }
        }


        return res;
    }

    public static void main(String[] args) {

        System.out.println(colorLeftRight("GGGGGGGRRGRGR"));

    }
}
