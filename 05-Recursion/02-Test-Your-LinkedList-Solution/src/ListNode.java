public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

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
        builder.append("NULL ] tail");
        builder.append("}");
        return builder.toString();
    }

}
