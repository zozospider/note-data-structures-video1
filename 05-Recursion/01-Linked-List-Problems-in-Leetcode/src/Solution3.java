public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        // 引入虚拟头节点, 是为了统一对所有节点的操作, 而不需要单独处理头部节点的特殊情况 (头部节点没有 prev)
        // 增加 / 删除操作: 需要改变链表结构, 会对节点本身和其前后节点进行操作, 所以一般使用 prev 来引用要操作的节点的前一个节点 (prev, prev.next, prev.next.next)
        // 查询 / 修改操作: 不需要改变链表结构, 只对节点本身进行操作, 所以一般使用 current 来引用要操作的节点 (current)
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;

        // 判断当前节点是否为 null, 如果为 null, 表示已遍历完所有节点
        while (prev.next != null) {

            // 只有在当前操作节点的值不等于 val 的时候, 才需要将 prev 引用进行移动, 否则需要再次判断 prev 引用的下一个节点是否需要继续删除
            // 如 [1 -> 3 -> 5]      链表中删除 3 的 prev 的引用变化为: prev(-1) [else 逻辑] -> prev(1) [if 逻辑执行删除] -> prev(5) [else 逻辑]
            // 如 [1 -> 3 -> 3 -> 5] 链表中删除 3 的 prev 的引用变化为: prev(-1) [else 逻辑] -> prev(1) [if 逻辑执行删除] -> prev(1) [if 逻辑执行删除] -> prev(5) [else 逻辑]

            if (prev.next.val == val) {
                // 如果当前节点的值等于 val, 需要将当前节点删除

                // 1. 考虑被删除节点的内存释放
                // ListNode remove = prev.next;
                // prev.next = remove.next;
                // remove.next = null;

                // 2 不考虑被删除节点的内存释放
                prev.next = prev.next.next;

            } else {
                // 遍历下一个节点的前一个节点
                prev = prev.next;
            }
        }

        // 返回头部节点 (代表整个链表)
        return dummyHead.next;
    }

}