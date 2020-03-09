public class LinkedList<E> {

    // 链表中的节点 (设置为 private 是为了屏蔽外部访问)
    private class Node {

        // 元素 (设置为 public 是为了方便 LinkedList 直接访问)
        public E e;

        // 下一个节点的引用 (设置为 public 是为了方便 LinkedList 直接访问)
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

}
