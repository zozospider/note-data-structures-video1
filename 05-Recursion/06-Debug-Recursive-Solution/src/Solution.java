public class Solution {

    public ListNode removeElements(ListNode head, int val, int depth) {

        String depthString = toDepthString(depth);
        System.out.print(depthString);
        System.out.println("call removeElements: " + head);

        if (head == null) {
            System.out.println(depthString + "return null");
            return null;
        }

        ListNode behind = removeElements(head.next, val, depth + 1);
        System.out.println(depthString + "after remove: " + head);

        ListNode result;
        if (head.val == val) {
            result = behind;
        } else {
            head.next = behind;
            result = head;
        }
        System.out.println(depthString + "return: " + result);
        return result;
    }

    private String toDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("- ");
        }
        return builder.toString();
    }

}
