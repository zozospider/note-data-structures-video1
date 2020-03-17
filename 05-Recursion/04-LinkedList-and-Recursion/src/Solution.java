public class Solution {

    // 此方法的宏观语义 (重点关注):
    // 删除以 head 为头节点的链表中等于 val 的所有节点, 返回删除后的链表的头节点

    // a. 将原始问题抽象成递归问题 (思考如何构造递归方法时一般会用到此过程)
    // 1. removeElements(N0 -> N1 -> N2 -> ... -> N(n-2) -> N(n-1), val)
    // 2. N0 -> removeElements(N1 -> N2 -> ... -> N(n-2) -> N(n-1), val)
    // 3. N0 -> N1 -> removeElements(N2 -> ... -> N(n-2) -> N(n-1), val)
    // ...
    // 4. N0 -> N1 -> ... + N(n-2) -> removeElements(n-1, val)
    // 5. N0 -> N1 -> ... + N(n-2) -> N(n-1) -> removeElements(n, val)

    // b. 将原始问题抽象成递归方法 (思考和编写递归代码时一般会用到此过程)
    // 从上到下为程序的实际调用顺序, 递归方法依次入栈: [1] -> [1, 2] -> [1, 2, 3] -> [1, 2, 3, 4] -> [1, 2, 3, 4, 5] top
    // 1. removeElements(N0, val);
    // 2. N0 -> removeElements(N1, val);
    // 3. N0 -> N1 -> removeElements(N2, val);
    // ...
    // 4. N0 -> N1 -> N2 -> N3 -> ... -> N(n-2) -> removeElements(N(n-1), val);
    // 5. N0 -> N1 -> N2 -> N3 -> ... -> N(n-2) -> N(n-1) -> removeElements(N(n), val);

    // c. 从上到下为程序的实际运算顺序, 递归方法依次出栈: [1, 2, 3, 4, 5] -> [1, 2, 3, 4] -> [1, 2, 3] -> [1, 2] -> [1] top
    // 5. N0 -> N1 -> N2 -> N3 -> ... -> N(n-2) -> N(n-1) -> (NULL);
    // 4. N0 -> N1 -> N2 -> N3 -> ... -> N(n-2) -> (N(n-1) -> NULL);
    // ...
    // 3. N0 -> N1 -> (N2 -> N3 -> ... -> N(n-2) -> N(n-1) -> NULL);
    // 2. N0 -> (N1 -> N2 -> N3 -> ... -> N(n-2) -> N(n-1) -> NULL);
    // 1. (N0 -> N1 -> N2 -> N3 -> ... -> N(n-2) -> N(n-1) -> NULL);

    public ListNode removeElements(ListNode head, int val) {

        // 递归终止 (最小的问题)
        if (head == null) {
            return null;
        }

        /* 方式一: 详细步骤

        // 递归调用 (更小的同等问题)
        // 删除链表 (以下一个节点为头节点) 中等于 val 的节点, 返回删除后的链表的头节点: [behind]
        ListNode behind = removeElements(head.next, val);

        // 执行删除逻辑
        if (head.val == val) {
            // 删除: 如果当前头节点等于 val, 则返回删除后的链表: return [behind]
            return behind;
        } else {
            // 不删除: 如果当前头节点不等于 val, 则返回当前头节点加删除后的链表: return [head -> behind]
            head.next = behind;
            return head;
        }
        */

        // 方式二: 简洁步骤

        // 递归调用 (更小的同等问题)
        // 删除链表 (以下一个节点为头节点) 中等于 val 的节点, 返回删除后的链表的头节点: [behind]
        // 将返回后的链表赋值到当前头节点的下一个节点引用: [head -> behind]
        head.next = removeElements(head.next, val);

        // 执行删除逻辑
        // 删除: 如果当前头节点等于 val, 则返回删除后的链表: return [behind]
        // 不删除: 如果当前头节点不等于 val, 则返回当前头节点加删除后的链表: return [head -> behind]
        return head.val == val ? head.next : head;
    }

    // 此方法的宏观语义 (重点关注):
    // 删除以 head 为头节点的链表中等于 val 的第一个节点, 返回删除后的链表的头节点
    public ListNode removeFirstElement(ListNode head, int val) {

        // 递归终止
        if (head == null) {
            return null;
        }

        // 递归终止
        if (head.val == val) {
            ListNode nextNode = head.next;
            head.next = null;
            return nextNode;
        }

        // 递归调用
        head.next = removeFirstElement(head.next, val);
        return head;
    }

}
