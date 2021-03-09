//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 840 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 有改进空间
 */
class Solution34 {
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        return process(0, nums.length - 1, nums, target);
    }

    public static int[] process(int left, int right, int[] nums, int target) {

        if (left >= right) {
            if (nums[left] == target) {
                return new int[]{left, left};
            } else {
                return new int[]{-1, -1};
            }
        }
        int[] ans = new int[]{-1, -1};
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            int[] leftAns = process(left, mid - 1, nums, target);
            int[] rightAns = process(mid + 1, right, nums, target);
            ans[0] = leftAns[0] < 0 ? mid : leftAns[0];
            ans[1] = Math.max(mid, rightAns[1]);
        } else if (nums[mid] < target) {
            ans = process(mid + 1, right, nums, target);
        } else {
            ans = process(left, mid - 1, nums, target);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = searchRange(new int[]{5, 7, 7, 8, 8, 1}, 8);
        System.out.println(ints);

        System.out.println(~6);

        System.out.println(Integer.toBinaryString(~6));

    }

}
//leetcode submit region end(Prohibit modification and deletion)
