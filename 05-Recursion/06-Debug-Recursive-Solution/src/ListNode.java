public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    // 使用 arr 为参数, 创建一个以当前 ListNode 为头结点的链表
    public ListNode(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode current = this;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        ListNode current = this;
        while (current != null) {
            builder.append(current.val).append(" -> ");
            current = current.next;
        }
        builder.append("NULL]");
        return builder.toString();
    }

}
