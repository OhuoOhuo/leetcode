//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 1145 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution32 {
    public static int longestValidParentheses(String s) {

        if(s==null && s.length()<2){
            return 0;
        }

        char[] chars = s.toCharArray();
        int res = 0;

        int[] dp = new int[chars.length];

        for (int i = 1; i <chars.length ; i++) {
            if(chars[i]=='('){
                continue;
            }else {
                int markIndex = i-1 -dp[i-1];
                int temp =0;
                if(markIndex >= 0&&chars[markIndex] == '('){
                    temp = dp[i-1]+2 + (markIndex-1<0?0:dp[markIndex-1]);
                }
                dp[i] =temp;
                res =Math.max(res,temp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = longestValidParentheses("())");
        System.out.println(i);


    }
}
//leetcode submit region end(Prohibit modification and deletion)
