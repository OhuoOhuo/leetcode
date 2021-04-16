package base.class12;



import java.util.HashMap;

/**
 * @author ：cwf
 * @description：剪纸问题
 *
 *   给定一个字符串str，给定一个字符串类型的数组arr。
 *   arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 *   返回需要至少多少张贴纸可以完成这个任务。
 *   例子：str= "babac"，arr = {"ba","c","abcd"}
 *   至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 *
 *   朴素的思维：我的第一个字母用哪张纸可以解决，使用这张纸，解决的剩下了哪些字母；其中多种做法中剩下最少使用的纸
 *
 *   数据变化：因为纸张可以剪碎，与顺序无关，只与字母种类和数量有关；可以把目标转换为[] ,贴纸列表也为[][];dp为缓存
 */
public class Code02_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {

        //把贴纸转换成二维数组

        //一共n张贴纸
        int n = stickers.length;
        //其中贴纸只有字母组成，如：[2][3][4][2]
        //                     [1][3][2][4]
        //                     表示有两张贴纸 aabbbccccdd aabbbccdddd  其中[2] 表示该贴纸在a位置上有两个，即为有两个a
        int[][] map = new int[n][26];
        for (int i = 0; i < n ; i++) {
            char[] str = stickers[i].toCharArray();
            for (int j = 0; j < str.length; j++) {
                map[i][str[j] -'a']++;
            }
        }

        HashMap<String, Integer> dp = new HashMap<>();
        //是个base case 当目标串为 "" 不需要贴纸
        dp.put("",0);

        return process(map,dp,target);
    }

    /**
     * 递归函数即为要求内容 在rest字符串情况下 最多需要几张纸
     * @param map  贴纸情况，不变
     * @param dp   缓存
     * @param rest 目标字符串
     * @return
     */
    private static int process(int[][] map, HashMap<String, Integer> dp, String rest) {
        //如果dp中已经缓存过该纸的值
        if(dp.containsKey(rest)){
            return dp.get(rest);
        }
        //默认最大
        int ans = Integer.MAX_VALUE;

        //把目标贴纸转换为数组形式
        int[]temp = new int[26];
        char[] target = rest.toCharArray();
        for (int i = 0; i < target.length ; i++) {
            temp[target[i]-'a']++;
        }

        for (int i = 0; i < map.length ; i++) {
            //这句话的含义是，只要包含第一个字符的贴纸才可以选择
            if(map[i][target[0]-'a']==0){
                continue;
            }
            StringBuffer stringBuffer = new StringBuffer();
            //选择了第i好贴纸，把对应的rest做处理
            for (int j = 0; j < 26 ; j++) {
                //这个字母是目标贴纸需要的
                if(temp[j] >0){
                    //该字母目标贴纸减去，选择该贴纸后还剩的数量,不可为负数
                    for (int k = 0; k < Math.max(0,temp[j]-map[i][j]) ; k++) {
                        stringBuffer.append((char)('a'+j));
                    }
                }
            }
            String s = stringBuffer.toString();
            int process = process(map, dp, s);
            if(process!=-1){
                ans = Math.min(ans,1+process);
            }
        }
        dp.put(rest,ans ==Integer.MAX_VALUE ? -1:ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String[] arr = {"aaaa","bbaa","ccddd"};
        String str = "abcccccdddddbbb";
        System.out.println(minStickers1(arr, str));
        System.out.println(minStickers2(arr, str));


    }

    private static int minStickers2(String[] stickers, String target) {
        if(target ==null || target.length()==0){
            return 0;
        }
        int num = stickers.length;
        int[][] map= new int[num][26];
        for (int i = 0; i < num; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c: chars) {
                map[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("",0);
        return process1(map,dp,target);
    }

    private static int process1(int[][] map, HashMap<String, Integer> dp, String rest) {
        if(dp.containsKey(rest)){
            return dp.get(rest);
        }
        int ans = Integer.MAX_VALUE;

        char[] target = rest.toCharArray();
        int[] temp =new int[26];
        for (char c: target) {
            temp[c-'a']++;
        }

        for (int i = 0; i < map.length ; i++) {
            if(map[i][target[0]-'a']==0){
                continue;
            }

            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < 26 ; j++) {
                for (int k = 0; k < Math.max(0,temp[j] - map[i][j]) ; k++) {
                    stringBuffer.append((char)('a'+j));
                }
            }
            String s = stringBuffer.toString();

            int process1 = process1(map, dp, s);

            if(process1!=-1){
                ans = Math.min(ans,1+process1);
            }
        }
        dp.put(rest,ans ==Integer.MAX_VALUE?-1:ans);
        return dp.get(rest);
    }


}
