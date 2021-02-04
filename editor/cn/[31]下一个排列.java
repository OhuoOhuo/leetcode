//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 
// 👍 928 👎 0


/**
 * 值得记录：
 * 1.什么叫下一个排列
 * 2.单调性 的具体应用
 * 经典算法
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution31 {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //找到后半截不是降序排列的第一个数
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //小于0 的情况为是完全倒序
        if(i>=0){
            int j = nums.length-1;
            while (nums[j] <=nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverce(nums,i+1);
    }

    //交换
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //翻转重i开始的链表
    private static void reverce(int[] nums, int i) {

        int j = nums.length-1;
        while (i < j ){
          swap(nums,i,j);
          i++;
          j--;
        }
    }


    public static void main(String[] args) {
       int[] arr= new int[]{1,5,1};
       nextPermutation(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
