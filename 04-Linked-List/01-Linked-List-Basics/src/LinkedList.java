public class LinkedList<E> {

    // 链表中的节点 (设置为 private 是为了屏蔽外部访问)
    private class Node {

        // 元素值
        E e;

        // 下一个节点的引用
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
            /*return "Node{" +
                    "e=" + e +
                    "}";*/
            /*return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';*/
        }
    }

}
