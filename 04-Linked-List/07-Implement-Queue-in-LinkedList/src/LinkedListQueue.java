public class LinkedListQueue<E> implements Queue<E> {

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

    // 头部节点 (第一个元素)
    private Node head;
    // 尾部节点 (最后一个元素)
    private Node tail;
    // 当前队列中的元素个数
    private int size;

    public LinkedListQueue() {
        // 初始化队列中没有元素, head 和 tail 都为 null
        head = null;
        tail = null;
        size = 0;
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return size;
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 时间复杂度: O(1)
    @Override
    public E getFront() {

        // 如果队列为空, 抛出异常
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        // 返回头部节点的元素值
        return head.e;
    }

    // 时间复杂度: O(1)
    @Override
    public void enqueue(E e) {

        // 尾部节点为 null 表示队列中没有元素
        if (tail == null) {

            // 队列中只有一个元素, 该元素的下一个节点为 null
            tail = new Node(e, null);

            // 队列中只有一个元素, head 和 tail 都指向该元素
            head = tail;

        } else {

            // 将本次入队的新节点增加到原尾部节点的下一个节点
            tail.next = new Node(e, null);

            // 设置尾部节点为本次入队的新节点
            tail = tail.next;
        }

        // 元素个数加 1
        size++;
    }

    // 时间复杂度: O(1)
    @Override
    public E dequeue() {

        // 如果队列为空, 抛出异常
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        // 1. 要出队的节点 (原头部节点)
        Node headNode = head;
        // 2. 设置原头部节点的下一个节点为头部节点 (因原头部节点已出队)
        head = head.next;
        // 3. 释放原头部节点的下一个节点的引用 (此时对它的引用在第 2 步已经修改, 它对其他节点的引用在第 3 步也已经修改, 因此 headNode 对应的堆内存对象满足垃圾回收条件)
        headNode.next = null;

        // 如果新的头部节点为 null, 表示队列中没有元素
        if (head == null) {
            // 设置 tail 为 null (tail 原指向原头部节点)
            tail = null;
        }

        // 元素个数减 1
        size--;

        // 返回要出队的节点的元素值
        return headNode.e;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("LinkedListQueue{");
        builder.append("size = ").append(size).append(", ");
        builder.append("head = ").append(head).append(", ");
        builder.append("tail = ").append(tail).append(", ");

        builder.append("data = front [");
        Node current = head;
        while (current != null) {
            builder.append(current).append(" -> ");
            current = current.next;
        }
        builder.append(" NULL] tail");

        builder.append("}");
        return builder.toString();

        /*return "LinkedListQueue{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';*/
    }

}
