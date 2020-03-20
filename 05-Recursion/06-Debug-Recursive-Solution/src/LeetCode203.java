/**
 * 203. 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class LeetCode203 {

    public static void main(String[] args) {
        // main1();
        main2();
    }

    // 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
    private static void main1() {

        int[] numbers = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(numbers);

        ListNode resultHead = new Solution().removeElements(head, 6, 0);
        System.out.println(resultHead);
    }

    // 6 -> 6 -> 1 -> 6 -> 6 -> 2 -> 6
    private static void main2() {

        int[] numbers = {6, 6, 1, 6, 6, 2, 6};
        ListNode head = new ListNode(numbers);

        ListNode resultHead = new Solution().removeElements(head, 6, 0);
        System.out.println(resultHead);
    }

}
