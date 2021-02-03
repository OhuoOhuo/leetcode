//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2131 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution20 {
    public boolean isValid(String s) {

        if (s == null || s.length() < 2) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i <chars.length ; i++) {
            if(chars[i] =='(' || chars[i]=='{' || chars[i] =='['){
                stack.push(chars[i]);
            }
            if(chars[i] == ')'){
                if(stack.isEmpty() || stack.pop() !='(') {
                    return false;
                }
            }
            if(chars[i] == ']'){
                if(stack.isEmpty() || stack.pop() !='[') {
                    return false;
                }
            }
            if(chars[i] == '}'){
                if(stack.isEmpty() || stack.pop() !='{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
