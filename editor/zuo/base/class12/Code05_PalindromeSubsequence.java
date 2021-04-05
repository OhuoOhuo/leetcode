package base.class12;

/**
 * @author ：cwf
 * @description：两个字符串的最长公共子序列
 */
public class Code05_PalindromeSubsequence {

    public static void main(String[] args) {
        String str1="aaaddaaaaabbbdddc";
        String str2="bbbbbaaadbbbcccc";

        int lcse = lcse(str1.toCharArray(), str2.toCharArray());

        System.out.println(maxSubsequence(str1,str2));
        System.out.println(lcse);
    }

    private static int maxSubsequence(String str1, String str2) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        return process(0,0,chars1,chars2);
    }

    /**
     *
     * 返回 从 i-> chars1 && j ->chars2 最长公共子序列
     * @param i chars1 考察的下标
     * @param j chars2 考察的下标
     * @param chars1 固定参数
     * @param chars2 固定参数
     * @return
     */
    private static int process(int i, int j, char[] chars1, char[] chars2) {

        if(i ==chars1.length -1 && j==chars2.length-1){
            return chars1[i] == chars2[j]?1:0;
        }

        //i到了最后,判断是否为1
        if(i+1 == chars1.length){
            int ans =0;
            for (int k = j; k < chars2.length  ; k++) {
                if(chars1[i] == chars2[k]){
                    ans=1;
                    break;
                }
            }
            return ans;
        }
        //j到了最后，判断是否为1
        if(j+1 ==chars2.length){
            int ans =0;
            for (int k = 0; k < chars1.length; k++) {
                if(chars2[j] ==chars1[k]){
                    ans=1;
                    break;
                }
            }
            return ans;
        }

        if(chars1[i] == chars2[j]){
            return process(i+1,j+1,chars1,chars2)+1;
        }else {
            return Math.max(process(i+1,j,chars1,chars2),process(i,j+1,chars1,chars2));
        }

    }


    public static int lcse(char[] str1, char[] str2) {




        int[][] dp = new int[str1.length][str2.length];



        dp[0][0] = str1[0] == str2[0] ? 1 : 0;


        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }


}
