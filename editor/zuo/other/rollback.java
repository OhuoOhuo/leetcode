package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：cwf
 * @description：回溯算法！！！
 *     全排列  &&  n皇后问题  &&  子集问题  && 组合
 *
 * 组合：
 * 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有组合。
 *
 * vector<vector<int>> combine(int n, int k);
 * 比如输入 n = 4, k = 2，输出如下结果，顺序无所谓，但是不能包含重复（按照组合的定义，[1,2] 和 [2,1] 也算重复）：
 *
 * [
 *  [1,2],
 *  [1,3],
 *  [1,4],
 *  [2,3],
 *  [2,4],
 *  [3,4]
 * ]
 *
 *
 * 框架：
 *
 * result = []
 * def backtrack(路径, 选择列表):
 * if 满足结束条件:
 * result.add(路径)
 * return
 * <p>
 * for 选择 in 选择列表:
 * 做选择
 * backtrack(路径, 选择列表)
 * 撤销选择
 * <p>
 *
 * 多叉树的遍历：（二叉树的遍历可以理解为特殊的多叉树遍历，所以多了个中序操作）
 * void traverse(TreeNode root) {
 * for (TreeNode child : root.childern)
 * // 前序遍历需要的操作
 * traverse(child);
 * // 后序遍历需要的操作
 * }
 */
public class rollback {

    public static void main(String[] args) {
        //全排列
        List<List<Integer>> permute = permute(new int[]{1, 2, 3, 4});
        System.out.println(permute);
        //N皇后问题
        List<int[]> ints = nQueues(8);
        System.out.println(ints.size());
        //子集问题
        List<List<Integer>> subList= subsets(new int[]{1,2,3});
        System.out.println(subList);
        List<List<Integer>> subList1= subsets1(new int[]{1,2,3});
        System.out.println(subList1);
        //组合
        System.out.println("组合！！！！");
        List<List<Integer>> combineList= combine(4,3);
        System.out.println(combineList);

    }

    private static List<List<Integer>> combine(int n, int k) {
        if(n<1 || k<1 || k>n){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();
        combineProcess(1,n,k,track,res);
        return res;
    }

    private static void combineProcess(int start, int n, int k, LinkedList<Integer> track, List<List<Integer>> res) {
        if(k == track.size()){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int j = start ; j <=n; j++) {
            track.add(j);
            combineProcess(j+1,n,k,track,res);
            track.removeLast();
        }

    }



    /**
     * 全排列问题
     */
    static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        permuteProcess(nums, track, result);
        return result;
    }

    private static void permuteProcess(int[] nums, LinkedList<Integer> track, ArrayList<List<Integer>> result) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            permuteProcess(nums, track, result);
            track.removeLast();
        }
    }


    private static List<List<Integer>> subsets1(int[] ints){
        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        subProcess1(0,ints,track,res);

        return res;

    }

    //普通递归方法
    private static void subProcess1(int cur, int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {

        if(cur==nums.length){
            res.add(new LinkedList<>(track));
            return;
        }else {
            //当前加入
            LinkedList<Integer> addTrack = new LinkedList<>(track);
            addTrack.add(nums[cur]);
            subProcess1(cur+1,nums,addTrack,res);
            //当前不加入
            LinkedList<Integer> noAddTrack = new LinkedList<>(track);
            subProcess1(cur+1,nums,noAddTrack,res);
        }

    }


    private static List<List<Integer>> subsets(int[] ints) {
        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        subProcess(0,ints,track,res);

        return res;
    }

    private static void subProcess(int start, int[] ints, LinkedList<Integer> track, List<List<Integer>> res) {
/*      for循环 已经带了这个判断，可以省略
        if(start ==ints.length){
            res.add(new LinkedList<>(track));
            return;
        }*/

        res.add(new LinkedList<>(track));
        for (int j = start; j < ints.length; j++) {
            track.add(ints[j]);
            subProcess(j+1,ints,track,res);
            track.removeLast();
        }
    }

    public static List<int[]> nQueues(int n) {
        List<int[]> res = new ArrayList<>();

        int[] track = new int[n];

        queueProcess(0, n, track, res);

        return res;
    }

    private static void queueProcess(int i, int n, int[] track, List<int[]> res) {
        if (i == n) {
            int[] clone = track.clone();
            res.add(clone);
            return;
        }

        //在第i行，判断改点是否可以添加
        for (int j = 0; j < n ; j++) {
            //不满足下一个
            if(!isValid(track,i,j)){
                continue;
            }
            track[i]=j;
            queueProcess(i+1,n,track,res);
            //恢复
            track[i]=0;
        }
    }

    //判断第i行，第j列，在已经record记录的基础上是否可以放
    private static boolean isValid(int[] record, int i, int j) {

        for (int k = 0; k < i ; k++) {
            //k record[k]  表示在k行 record[k]列  有皇后  和 i 行  j列 进行判断
            //如果两个点在同一列 或者 在同一斜线上返回false
            if(record[k]==j||Math.abs(i-k) ==Math.abs(j-record[k]) ){
                return false;
            }
        }
        return true;
    }


}
