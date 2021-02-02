//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 5553 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import org.w3c.dom.ls.LSOutput;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resNode = new ListNode();

        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode resPre = resNode;

        boolean isAdd = false;

        while (head1 != null || head2 != null) {
            int temp1 = 0;
            int temp2 = 0;
            if (head1 != null) {
                temp1 = head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                temp2 = head2.val;
                head2 = head2.next;
            }
            int temp3 = temp1 + temp2;
            if (isAdd) {
                temp3 += 1;
            }
            if (temp3 >= 10) {
                isAdd = true;
            } else {
                isAdd = false;
            }
            ListNode listNode = new ListNode(temp3 % 10);
            resPre.next = listNode;
            resPre = resPre.next;
        }
        if(isAdd){
            resPre.next=new ListNode(1);
        }

        return resNode.next;
    }

/*    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next=new ListNode(4);
        l1.next.next =new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next=new ListNode(6);
        l2.next.next =new ListNode(4);

        addTwoNumbers(l1,l2);

    }*/
}
//leetcode submit region end(Prohibit modification and deletion)
