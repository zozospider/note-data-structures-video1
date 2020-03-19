public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 映射中的节点
    private class Node {

        // key
        K key;

        // value
        V value;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        public Node (K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 根节点
    private Node root;

    // 当前映射中的元素个数 (当前二分搜索树中的元素个数)
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    // 映射元素的大小 (获取树中的元素个数)
    @Override
    public int getSize() {
        return size;
    }

    // 映射是否为空 (返回树是否为空)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 映射中是否包含 key (二分搜索树中是否包含元素 (key - value))
    @Override
    public boolean contains(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 不为 null 则存在, 否则不存在
        return getNode(root, key) != null;
    }

    // 获取映射中 key 对应的 value
    @Override
    public V get(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则返回 Node 的 value
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 将元素 (key - value) 添加到映射中 (向二分搜索树中添加元素 (key - value)) (如果 key 已存在, 则修改 key 对应的 value)
    @Override
    public void add(K key, V value) {

        // 在以 root 为根节点的二分搜索树中添加一个元素 (key - value)
        root = add(root, key, value);
    }

    // 此方法的宏观语义 (重点关注):
    // 向以 node 为根的二分搜索树中插入元素 (key - value), 并返回该二分搜索树的根
    // 如果 node 为 null, 则插入元素 (key - value) 到根位置, 然后返回根
    // 如果 node 不为 null, 则插入元素 (key - value) 到 node 的左孩子或右孩子
    private Node add(Node node, K key, V value) {

        // 递归终止
        // 当前传入的根节点为 null, 则返回当前新创建的根节点
        if (node == null) {
            // 元素个数加 1
            size++;
            return new Node(key, value, null, null);
        }

        return null;
    }

    // 修改映射中 key 对应的 value
    @Override
    public void set(K key, V value) {

    }

    // 将 key 从映射中删除
    @Override
    public V remove(K key) {
        return null;
    }

    // 获取 key 对应的 Node
    // 返回以 node 为根节点的二分搜索树中, key 所在的节点
    private Node getNode(Node node, K key) {

        // 递归终止
        // node 为 null, 则未找到 Node
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {

            // 递归终止
            // 如果 key 等于 node 的 key, 则找到 Node
            return node;

        } else if (key.compareTo(node.key) < 0) {

            // 递归调用
            // 如果 key 在 node 的左孩子中, 则返回以 node 的左孩子为根的二分搜索树中 key 所在的节点
            return getNode(node.left, key);

        } else { // key.compareTo(node.key) > 0

            // 递归调用
            // 如果 key 在 node 的右孩子中, 则返回以 node 的右孩子为根的二分搜索树中 key 所在的节点
            return getNode(node.right, key);
        }
    }

}
