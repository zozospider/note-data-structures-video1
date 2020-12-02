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

    // 虚拟头节点 (第一个节点之前的虚拟节点)
    // 引入虚拟头节点, 是为了统一对所有节点的操作, 而不需要单独处理头部节点的特殊情况 (头部节点没有 prev)
    // 增加 / 删除操作: 需要改变链表结构, 会对节点本身和其前后节点进行操作, 所以一般使用 prev 来引用要操作的节点的前一个节点 (prev, prev.next, prev.next.next)
    // 查询 / 修改操作: 不需要改变链表结构, 只对节点本身进行操作, 所以一般使用 current 来引用要操作的节点 (current)
    private Node dummyHead;

    // 当前链表中的元素个数
    private int size;

    public LinkedList() {
        // 初始化时虚拟头节点没有数据, 且尚未指向第一个节点
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表中的元素个数
    // 时间复杂度: O(1)
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    // 时间复杂度: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取链表的第 index(0-based) 个位置的元素
    // 注: 因链表本身的结构特性不适合在随机位置查询, 此方法在链表中不是一个常用的操作, 练习用
    // 时间复杂度: O(1/2n) = O(n)
    public E get(int index) {

        // 如果要获取的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        // 最终得到要查询的 index 节点 (从头部节点开始算第一个)
        // 也可以理解成用于记录某个索引的节点 (从虚拟头节点的下一个节点开始)
        Node current = dummyHead.next;

        // 从头部节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 节点的引用: current Node = index Node = (index - 1).next Node
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // 返回元素值
        return current.e;
    }

    // 获取链表的第一个元素
    // 时间复杂度: O(1)
    public E getFirst() {
        return get(0);
    }

    // 获取链表的最后一个元素
    // 时间复杂度: O(n)
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第 index(0-based) 个位置的元素为 e
    // 注: 因链表本身的结构特性不适合在随机位置修改, 此方法在链表中不是一个常用的操作, 练习用
    public void set(int index, E e) {

        // 如果要设置的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }

        // 最终得到要修改的 index 节点 (从头部节点开始算第一个)
        // 也可以理解成用于记录某个索引的节点 (从虚拟头节点的下一个节点开始)
        Node current = dummyHead.next;

        // 从头部节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 节点的引用: current Node = index Node = (index - 1).next Node
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // 修改元素值
        current.e = e;
    }

    // 查找链表中是否有元素 e
    // 时间复杂度: O(n)
    public boolean contains(E e) {

        // 用于记录某个节点 (从 虚拟头节点的下一个节点 / 头部节点 开始算第一个)
        Node current = dummyHead.next;

        // 从头部节点开始, 依次遍历链表中的每个节点, 并判断节点对应的元素值是否等于传入的参数值
        while (current != null) {
            if (current.e.equals(e)) {
                return true;
            }
            // current 赋值为下一个节点的引用
            current = current.next;
        }

        /* 另一种写法
        for (Node current = dummyHead.next; current != null ; current = current.next) {
            if (current.e.equals(e)) {
                return true;
            }
        }
        */

        return false;
    }

    // 在链表的 index(0-based) 位置添加新的元素 e
    // 注: 因链表本身的结构特性不适合在随机位置插入, 此方法在链表中不是一个常用的操作, 练习用
    // 时间复杂度: O(1/2n) = O(n)
    public void add(int index, E e) {

        // 如果要插入的索引位置小于 0, 或者大于当前 size 索引位置 (即所有元素最后位置之后), 则认为是非法操作, 抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index is illegal.");
        }

        // 最终得到要插入的 index 节点的前一个节点 (从虚拟头节点开始算第一个)
        // 也可以理解成用于记录某个索引的前一个节点 (从头部节点的前一个节点开始)
        Node prev = dummyHead;

        // 从虚拟头节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 前一个节点的引用: prev Node = (index - 1) Node = (index - 2).next Node
        for (int i = 0; i < index; i++) {
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

    // 在链表的头部添加新的元素 e
    // 时间复杂度: O(1)
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表的尾部添加新的元素 e
    // 时间复杂度: O(n)
    public void addLast(E e) {
        add(size, e);
    }

    // 从链表中删除 index(0-based) 位置的元素, 返回删除的元素
    // 注: 因链表本身的结构特性不适合在随机位置删除, 此方法在链表中不是一个常用的操作, 练习用
    // 时间复杂度: O(1/2n) = O(n)
    public E remove(int index) {

        // 如果要删除的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        // 最终得到要删除的 index 节点的前一个节点 (从虚拟头节点开始算第一个)
        // 也可以理解成用于记录某个索引的前一个节点 (从头部节点的前一个节点开始)
        Node prev = dummyHead;

        // 从虚拟头节点开始, 通过节点的 next 引用, 依次找下一个节点, 直到找到 index 前一个节点的引用: prev Node = (index - 1) Node = (index - 2).next Node
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // 1. 获取要删除节点 (要删除节点的前一个节点的下一个节点)
        Node remove = prev.next;
        // 2. 将要删除节点的前一个节点的下一个节点的引用修改为要删除节点的下一个节点, 即跳过了要删除节点
        prev.next = remove.next;
        // 3. 释放将要删除节点的下一个节点的引用 (此时它的上一个节点对它的引用在第 2 步已经修改, 它对其他节点的引用在第 3 步也已经修改, 因此 remove 对应的堆内存对象满足垃圾回收条件)
        remove.next = null;

        // 元素个数减 1
        size--;

        // 返回删除节点的元素值
        return remove.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    // 时间复杂度: O(1)
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    // 时间复杂度: O(n)
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素 e
    // 时间复杂度: O(n)
    public void removeElement(E e) {

        // 最终得到要删除的节点的前一个节点 (从虚拟头节点开始算第一个)
        // 也可以理解成用于记录某个节点的前一个节点 (从头部节点的前一个节点开始)
        Node prev = dummyHead;

        // 从虚拟头节点开始, 通过节点的 next 引用, 依次找下一个节点, 并判断下一个节点对应的元素值是否等于传入的参数值
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            // prev 赋值为下一个节点的前一个节点的引用
            prev = prev.next;
        }

        // 如果 prev.next (即当前节点) 不为 null, 说明找到了需要删除的节点
        if (prev.next != null) {

            // 1. 获取要删除节点 (要删除节点的前一个节点的下一个节点)
            Node remove = prev.next;
            // 2. 将要删除节点的前一个节点的下一个节点的引用修改为要删除节点的下一个节点, 即跳过了要删除节点
            prev.next = remove.next;
            // 3. 释放将要删除节点的下一个节点的引用 (此时它的上一个节点对它的引用在第 2 步已经修改, 它对其他节点的引用在第 3 步也已经修改, 因此 remove 对应的堆内存对象满足垃圾回收条件)
            remove.next = null;

            // 元素个数减 1
            size--;
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("LinkedList{");

        Node current = dummyHead.next;
        while (current != null) {
            builder.append(current).append(" -> ");
            current = current.next;
        }
        builder.append("NULL");

        /*for (Node current = dummyHead.next; current != null ; current = current.next) {
            builder.append(current + " -> ");
        }*/

        builder.append("}");
        return builder.toString();
    }

}
