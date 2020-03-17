public class Solution {

    // 删除以 head 为头节点的链表中等于 val 的所有节点, 返回删除后的链表的头节点
    public ListNode removeElements(ListNode head, int val) {

        // 因为头节点没有 prev 节点, 所以需要单独处理
        while (head != null && head.val == val) {

            // a. 考虑被删除节点的主动内存释放
            ListNode remove = head;
            head = head.next;
            remove.next = null;

            // b. 不考虑被删除节点的主动内存释放
            // head = head.next;
        }

        if (head == null) {
            return null;
        }

        // 判断当前节点是否为 null, 如果为 null, 表示已遍历完所有节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {

                // a. 考虑被删除节点的主动内存释放
                ListNode remove = prev.next;
                prev.next = remove.next;
                remove.next = null;

                // b. 不考虑被删除节点的主动内存释放
                // prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

}
