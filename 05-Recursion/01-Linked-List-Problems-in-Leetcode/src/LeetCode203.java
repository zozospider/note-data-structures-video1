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
        main1();
        main2();
    }

    // 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
    private static void main1() {
        ListNode seven = new ListNode(6);
        ListNode six = new ListNode(5);
        six.next = seven;
        ListNode five = new ListNode(4);
        five.next = six;
        ListNode four = new ListNode(3);
        four.next = five;
        ListNode three = new ListNode(6);
        three.next = four;
        ListNode two = new ListNode(2);
        two.next = three;
        ListNode one = new ListNode(1);
        one.next = two;

        ListNode resultHead = new Solution2().removeElements(one, 6);
        System.out.println(resultHead);
    }

    // 6 -> 6 -> 1 -> 6 -> 6 -> 2 -> 6
    private static void main2() {
        ListNode seven = new ListNode(6);
        ListNode six = new ListNode(2);
        six.next = seven;
        ListNode five = new ListNode(6);
        five.next = six;
        ListNode four = new ListNode(6);
        four.next = five;
        ListNode three = new ListNode(1);
        three.next = four;
        ListNode two = new ListNode(6);
        two.next = three;
        ListNode one = new ListNode(6);
        one.next = two;

        ListNode resultHead = new Solution2().removeElements(one, 6);
        System.out.println(resultHead);
    }

}
