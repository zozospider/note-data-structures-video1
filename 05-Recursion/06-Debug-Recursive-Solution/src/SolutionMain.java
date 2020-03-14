/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 203. 移除链表元素
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class SolutionMain {

    public static void main(String[] args) {
        // main1();
        main2();
    }

    // 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
    private static void main1() {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);

        ListNode resultHead = new Solution().removeElements(head, 6, 0);
        System.out.println(resultHead);
    }

    // 6 -> 6 -> 1 -> 6 -> 6 -> 2 -> 6
    private static void main2() {

        int[] nums = {6, 6, 1, 6, 6, 2, 6};
        ListNode head = new ListNode(nums);

        ListNode resultHead = new Solution().removeElements(head, 6, 0);
        System.out.println(resultHead);
    }

}
