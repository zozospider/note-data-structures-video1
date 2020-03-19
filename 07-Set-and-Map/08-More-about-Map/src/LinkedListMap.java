public class LinkedListMap<K, V> implements Map<K, V> {

    // 映射中的节点
    private class Node {

        // key
        K key;

        // value
        V value;

        // 下一个节点的引用
        Node next;

        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }

    }

    // 虚拟头节点 (第一个节点之前的虚拟节点)
    // 引入虚拟头节点, 是为了统一对所有节点的操作, 而不需要单独处理头部节点的特殊情况 (头部节点没有 prev)
    // 增加 / 删除操作: 需要改变映射结构, 会对节点本身和其前后节点进行操作, 所以一般使用 prev 来引用要操作的节点的前一个节点 (prev, prev.next, prev.next.next)
    // 查询 / 修改操作: 不需要改变映射结构, 只对节点本身进行操作, 所以一般使用 current 来引用要操作的节点 (current)
    private Node dummyHead;

    // 当前映射中的元素个数
    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null, null, null);
        size = 0;
    }

    // 映射元素的大小
    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return size;
    }

    // 映射是否为空
    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 映射中是否包含 key
    @Override
    public boolean contains(K key) {

        // 获取 key 对应的 Node, 如果 Node 不为 null 则存在, 否则不存在
        return getNode(key) != null;
    }

    // 获取映射中 key 对应的 value
    @Override
    public V get(K key) {

        // 获取 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则返回 Node 的 value
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    // 将元素 (key - value) 添加到映射中 (如果 key 已存在, 则修改 key 对应的 value)
    @Override
    public void add(K key, V value) {

        // 获取 key 对应的 Node
        Node node = getNode(key);

        // 如果 Node 为 null, 则将新创建的 Node 节点添加到映射头部, 并修改相关引用
        // 如果 Node 不为 null, 则修改 Node 的 value
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }
    }

    // 修改映射中 key 对应的 value
    @Override
    public void set(K key, V value) {

        // 获取 key 对应的 Node
        Node node = getNode(key);

        // 如果 Node 为 null, 则无法修改, 抛出异常
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }

        // 修改 Node 的 value
        node.value = value;
    }

    // 将 key 从映射中删除
    @Override
    public V remove(K key) {

        // 最终得到要删除的节点的前一个节点 (从虚拟头节点开始算第一个)
        // 也可以理解成用于记录某个节点的前一个节点 (从头部节点的前一个节点开始)
        Node prev = dummyHead;

        // 从虚拟头节点开始, 通过节点的 next 引用, 依次找下一个节点, 并判断下一个节点对应的 key 是否等于传入的参数值
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
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

            // 返回被删除节点的 value
            return remove.value;
        }

        // 否则 prev.next (即当前节点) 为 null, 说明未找到需要删除的节点, 返回 null
        return null;
    }

    // 获取 key 对应的 Node
    private Node getNode(K key) {

        // 用于记录某个节点 (从 虚拟头节点的下一个节点 / 头部节点 开始算第一个)
        Node current = dummyHead.next;

        // 从头部节点开始, 依次遍历映射中的每个节点, 并判断节点对应的 key 值是否等于传入的参数值
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }

        // 如果未找到, 则返回 null
        return null;
    }

}
