package base.class11;

/**
 * @author ：cwf
 * @description：打印字符串的子序列
 * 如果需要去重，可以set去重
 */
public class Code02_PrintAllSubsquences {

    public static void main(String[] args) {
        printAllSub("aacc");
    }

    private static void printAllSub(String str) {

        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();
        String track = "";
        process(0, chars, track);
    }

    private static void process(int i, char[] chars, String track) {
        if (i == chars.length) {
            System.out.println(track);
            return;
        }
        process(i+1,chars,track);
        process(i+1,chars,track+chars[i]);

    }


}
