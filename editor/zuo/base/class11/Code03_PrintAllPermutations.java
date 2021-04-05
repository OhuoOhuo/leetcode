package base.class11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：cwf
 * @description：字符串的全排列
 */
public class Code03_PrintAllPermutations {
    public static void main(String[] args) {

        String test = "asdf";
        //System.out.println(test.substring(0,test.length()-1));

        List<String> stringList = getAllPermutations("abc");
        for (String str: stringList) {
            System.out.println(str);
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<String> stringList1 =getAllPermutations1("abc");
        for (String str: stringList1) {
            System.out.println(str);
        }
    }

    private static List<String> getAllPermutations1(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process1(chs, 0, res);
        return res;
    }

    private static void process1(char[] chs, int i, ArrayList<String> res) {
        if(i == chs.length){
            res.add(String.valueOf(chs));
            return;
        }
        for (int j = i; j < chs.length ; j++) {
            swap(chs,i,j);
            process1(chs,i+1,res);
            swap(chs,i,j);
        }
    }

    private static void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }

    private static List<String> getAllPermutations(String str) {
        if(str ==null){
            return null;
        }

        ArrayList<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        LinkedList<Character> track = new LinkedList<>();
        process(0,track, chars,res);
        return res;
    }

    private static void process(int i, LinkedList<Character> track, char[] chars, ArrayList<String> res) {
        if(track.size() == chars.length){
            String trackStr = "";
            for (Character str: track) {
                trackStr = trackStr + str;
            }
            res.add(trackStr);
            return;
        }

        for (int j = 0; j < chars.length ; j++) {
            if(track.contains(chars[j])){
                continue;
            }
            track.add(chars[j]);
            process(i+1,track,chars,res);
            track.removeLast();
        }
    }
}
