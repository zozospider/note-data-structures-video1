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

    // 头部节点
    private Node head;

    // 当前链表中的元素个数
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表的头部添加新的元素 e
    public void addFirst(E e) {

        // 1. 创建数据为 e 的节点
        // Node node = new Node(e);
        // 2. 设置节点的下一个节点的引用为 LinkedList 的头部节点
        // node.next = head;
        // 3. 将 LinkedList 的头部节点的引用设置为当前创建的节点, 即表示当前创建的节点为新的头部节点
        // head = node;

        // 等效于上面 3 个步骤
        // new Node(e, head); 等效于步骤 1, 2
        // head = new Node(e, head); 等效于步骤 3
        head = new Node(e, head);

        // 元素个数加 1
        size++;
    }

    public void add(int index, E e) {

        // 如果要插入的索引位置小于 0, 或者大于当前 size 索引位置 (即所有元素最后位置之后), 则认为是非法操作, 抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index is illegal.");
        }

        // 因为索引为 0 的节点没有前面的节点, 不满足下面的逻辑, 需要特殊处理
        if (index == 0) {
            addFirst(e);
        } else {

            // 记录要插入的 index 节点的前一个节点
            Node pre = head;

            // 从头部节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 前一个元素的引用: pre Node = (index - 1) Node = (index - 2).next Node
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }

            // 1. 创建数据为 e 的节点
            // Node node = new Node(e);
            // 2. 设置当前创建的节点的下一个节点的引用为 pre Node (index 前一个元素的引用) 的下一个节点的引用 pre.next Node
            // node.next = pre.next;
            // 3. 将 pre.next 的头部节点的引用设置为当前创建的节点, 即表示当前创建的节点为 pre Node 的下一个节点
            // pre.next = node;

            // 等效于上面 3 个步骤
            // new Node(e, pre.next); 等效于步骤 1, 2
            // pre.next = new Node(e, pre.next); 等效于步骤 3
            pre.next = new Node(e, pre.next);

            // 元素个数加 1
            size++;
        }
    }

    // 在链表的尾部添加新的元素 e
    public void addLast(E e) {
        add(size, e);
    }

}
