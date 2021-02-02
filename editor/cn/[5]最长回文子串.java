//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3148 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        char[] palChars = new char[chars.length * 2 + 1];

        for (int i = 0; i < palChars.length; i++) {
            if (i % 2 == 1) {
                palChars[i] = chars[(i - 1) / 2];
            } else {
                palChars[i] = '#';
            }
        }

        int max = 0;
        int L = 0;
        int R = 0;
        for (int i = 0; i < palChars.length; i++) {
            int left = i - 1;
            int right = i + 1;

            while (left>=0 && right<=palChars.length -1 && palChars[left]==palChars[right]){
                left--;
                right++;
            }
            if(right -left -1>max){
                max = right-left-1;
                L = left +1;
                R = right-1;
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = L; i <= R; i++) {
            if(palChars[i] !='#'){
                stringBuffer.append(palChars[i]);
            }
        }

        return stringBuffer.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
