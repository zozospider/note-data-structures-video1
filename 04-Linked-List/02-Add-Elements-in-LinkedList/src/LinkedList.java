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

    // 在链表的 index(0-based) 位置添加新的元素 e
    // 注: 因链表本身的结构特性不适合在随机位置插入, 此方法在链表中不是一个常用的操作, 练习用
    public void add(int index, E e) {

        // 如果要插入的索引位置小于 0, 或者大于当前 size 索引位置 (即所有元素最后位置之后), 则认为是非法操作, 抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index is illegal.");
        }

        // 因为索引为 0 的节点没有前面的节点, 不满足下面的逻辑, 需要特殊处理
        if (index == 0) {
            addFirst(e);
        } else {

            // 最终得到要插入的 index 节点的前一个节点 (从头部节点开始算第一个)
            // 也可以理解成用于记录某个索引的前一个节点 (从第二个节点的前一个节点开始)
            Node prev = head;

            // 从头部节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 前一个节点的引用: prev Node = (index - 1) Node = (index - 2).next Node
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            // 1. 创建数据为 e 的节点
            // Node node = new Node(e);
            // 2. 设置当前创建的节点的下一个节点的引用为 prev Node (index 前一个节点的引用) 的下一个节点的引用 prev.next Node
            // node.next = prev.next;
            // 3. 将 prev.next 的头部节点的引用设置为当前创建的节点, 即表示当前创建的节点为 prev Node 的下一个节点
            // prev.next = node;

            // 等效于上面 3 个步骤
            // new Node(e, prev.next); 等效于步骤 1, 2
            // prev.next = new Node(e, prev.next); 等效于步骤 3
            prev.next = new Node(e, prev.next);

            // 元素个数加 1
            size++;
        }
    }

    // 在链表的尾部添加新的元素 e
    public void addLast(E e) {
        add(size, e);
    }

}
