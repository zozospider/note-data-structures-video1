public class Solution {

    public ListNode removeElements(ListNode head, int val, int depth) {

        String depthString = toDepthString(depth);
        System.out.println(depthString + "call removeElements     |" + head);

        if (head == null) {
            System.out.println(depthString + "return removeElements   |null (stop recursion)");
            return null;
        }

        ListNode behind = removeElements(head.next, val, depth + 1);
        System.out.println(depthString + "get behind removeElements |" + behind);

        ListNode result;
        if (head.val == val) {
            result = behind;
            System.out.println(depthString + "return removeElements     |" + result + " (drop " + val + ")");
        } else {
            head.next = behind;
            result = head;
            System.out.println(depthString + "return removeElements     |" + result);
        }
        return result;
    }

    private String toDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        builder.append("deep ").append(depth).append(" ");
        for (int i = 0; i < depth; i++) {
            builder.append("- ");
        }
        return builder.toString();
    }

}
