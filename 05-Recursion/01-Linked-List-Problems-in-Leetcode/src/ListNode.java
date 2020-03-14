public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListNode{");
        builder.append("data = head [");
        ListNode current = this;
        while (current != null) {
            builder.append(current.val).append(" -> ");
            current = current.next;
        }
        builder.append("NULL] tail");
        builder.append("}");
        return builder.toString();
    }

}
