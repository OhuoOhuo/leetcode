//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
//  
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 回溯算法 
// 👍 1541 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    public static List<String> generateParenthesis(int n) {
        List<String> process = process(2 * n - 1);
        ArrayList<String> ans = new ArrayList<>();

        for (String str: process) {
            if(isValidStr(str)){
                ans.add(str);
            }
        }
        return ans;
    }

    private static boolean isValidStr(String str) {
        char[] chars = str.toCharArray();
        int count =0;

        for (int i = 0; i < chars.length; i++) {
           if(chars[i] == '('){
               count++;
           }else {
               count--;
           }
           if(count<0){
               return false;
           }
        }

        return count==0;

    }

    private static List<String> process(int i) {
        if(i ==0){
            ArrayList<String> strings = new ArrayList<>();
            strings.add("(");
            strings.add(")");
            return strings;
        }
        List<String> process = process(i - 1);
        ArrayList<String> strings = new ArrayList<>();
       for (int j = 0; j < process.size() ; j++) {
            strings.add(process.get(j)+"(");
            strings.add(process.get(j)+")");
        }

        return strings;
    }

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
