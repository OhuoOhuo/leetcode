//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1109 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution17 {
    public static List<String> letterCombinations(String digits) {

        if(digits==null || digits.length()<1){
            return new ArrayList<String>();
        }

        HashMap<Character, String[]> map = new HashMap<>();
        String[] characters2 = {"a","b","c"};
        String[] characters3 = {"d","e","f"};
        map.put('2', characters2);map.put('3',characters3);map.put('4', new String[]{"g", "h", "i"});
        map.put('5',new String[]{"j","k","l"});map.put('6',new String[]{"m","n","o"});map.put('7',new String[]{"p","q","r","s"});
        map.put('8',new String[]{"t","u","v"});map.put('9',new String[]{"w","x","y","z"});

        char[] chars = digits.toCharArray();

        return process(chars.length -1,chars,map);
    }

    private static List<String> process(int i, char[] chars, HashMap<Character, String[]> map) {
        if(i  ==0 ){
            String[] strings = map.get(chars[0]);
            List<String> strings1 = new ArrayList<>();
            for (String str: strings) {
                strings1.add(str);
            }
            return strings1;
        }
        List<String> ansList = process(i - 1, chars, map);

        ArrayList<String> strings1 = new ArrayList<>();
        String[] strings = map.get(chars[i]);
        for (String str: ansList) {
            for (String str1: strings) {
                strings1.add(str+str1);
            }
        }

        return strings1;
    }

    public static void main(String[] args) {
        List<String> strings = letterCombinations("239");
        System.out.println(strings);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
