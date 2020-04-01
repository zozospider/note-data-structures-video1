public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // 红黑树中的节点
    private class Node {

        // key
        K key;

        // value
        V value;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        // 颜色 (红色 true, 黑色 false)
        boolean color;

        Node(K key, V value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        Node(K key, V value, Node left, Node right) {
            this(key, value, left, right, RED);
        }
    }

    // 根节点
    private Node root;

    // 当前红黑树中的元素个数 (当前二分搜索树中的元素个数)
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    // 红黑树元素的大小 (获取树中的元素个数)
    public int getSize() {
        return size;
    }

    // 红黑树是否为空 (返回树是否为空)
    public boolean isEmpty() {
        return size == 0;
    }

    // 红黑树中是否包含 key (二分搜索树中是否包含元素 (key - value))
    public boolean contains(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 不为 null 则存在, 否则不存在
        return getNode(root, key) != null;
    }

    // 获取红黑树中 key 对应的 value
    public V get(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则返回 Node 的 value
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 修改红黑树中 key 对应的 value
    public void set(K key, V value) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node
        Node node = getNode(root, key);

        // 如果 Node 为 null, 则无法修改, 抛出异常
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }

        // 修改 Node 的 value
        node.value = value;
    }

    // 向红黑树中添加新的元素 (key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    // 以下为 add(Node node, K key, V value) 方法添加一个节点, 23树和红黑树在多种不同的情况下的结构变化和对应的代码处理如下:
    // [] 表示23树中的节点, 其中三节点中的两个元素用 `,` 分割, 如 [10,20]
    // () 表示红黑树中的黑色节点, 如 (20)
    // <> 表示红黑树中的红色节点, 如 <10>
    // ---------------------------------------------------------------

    // case-1: 向23树中添加第一个节点 (可能出现)
    // [] +[20]

    // []  ->  [20]

    //     -new Node
    // ()  ->  (20)

    // ---------------------------------------------------------------

    // case-2A: 向23树中的二节点左边添加一个节点 (可能出现)
    // [20] +[10]

    // [20]  ->  [10,20]

    //       -add
    // (20)  ->     (20)
    //           <10>

    // ---------------------------------------------------------------

    // case-2B: 向23树中的二节点右边添加一个节点 (可能出现)
    // [20] +[40]

    // [20]  ->  [20,40]  ->  [20,40]

    //       -add         -leftRotate
    // (20)  ->  (20)     ->     (40)
    //              <40>      <20>

    // ---------------------------------------------------------------

    // case-3A: 向23数中的三节点右边添加一个节点 (可能出现)
    // [20,40] +[50]

    // [20,40]  ->  [20,40,50]  ->     [40]
    //                              [20]  [50]

    //          -add           -flipColors
    //    (40)  ->     (40)    ->      <40>
    // <20>         <20>  <50>      (20)  (50)

    // ---------------------------------------------------------------

    // case-3B: 向23数中的三节点左边添加一个节点 (可能出现)
    // [20,40] +[10]

    // [20,40]  ->  [10,20,40]  ->  [10,20,40]  ->     [20]
    //                                              [10]  [40]

    //          -add            -rightRotate    -flipColors
    //    (40)  ->        (40)  ->     (20)     ->     <20>
    // <20>            <20>         <10>  <40>      (10)  (40)
    //              <10>

    // ---------------------------------------------------------------

    // case-3C: 向23数中的三节点中间添加一个节点 (理论上不会出现)
    // 注: 这种情况理论上不会出现, 因为当前操作的是 node 40, 由于 add 方法的结构调整顺序是从下到上遍历, 在上一次遍历 node 20 的时候, node 20 就已经在 node 30 左下方了 (即执行 case-2B 的逻辑)
    // 也就是说 leftRotate, rightRotate, flipColors 不会在一次 add 递归中都执行
    // [20,40] +[30]

    // [20,40]  ->  [20,30,40]  ->  [20,30,40]  ->  [20,30,40]  ->     [30]
    //                                                              [20]  [40]

    //          -add            -leftRotate     -rightRotate    -flipColors
    //    (40)  ->     (40)     ->        (40)  ->     (30)     ->     <30>
    // <20>         <20>               <30>         <20>  <40>      (20)  (40)
    //                 <30>         <20>

    // ---------------------------------------------------------------

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, null, null);
        }

        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
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

    // 左旋转 (对应 23 树的结构不发生变化)

    // 示例: 对 node 20 进行左旋转

    // [10,20,30]  ->  [10,20,30]

    // (10)        ->     (20)
    //    <20>         <10>  <30>
    //       <30>
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        // 由于 23 树的结构不发生变化, 所以顶部节点的颜色要保持不变
        // 由于 23 树节点为四节点, 所以对应红黑树中的子节点为红色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 右旋转 (对应 23 树的结构不发生变化)

    // 示例: 对 node 20 进行右旋转

    // [10,20,30]  ->  [10,20,30]

    //       (30)  ->     (20)
    //    <20>         <10>  <30>
    // <10>
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        // 由于 23 树的结构不发生变化, 所以顶部节点的颜色要保持不变
        // 由于 23 树节点为四节点, 所以对应红黑树中的子节点为红色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 颜色翻转 (对应 23 树中的分裂 & 向上融合)

    // 示例: 对 node 20 进行颜色翻转

    // [10,20,30]  ->     [20]
    //                 [10]  [30]

    //    (20)     ->     <20>
    // <10>  <30>      (10)  (30)
    private void flipColors(Node node) {
        // 23 树分裂后当前节点向上融合, 向上融合时充当新节点角色, 所以为红色
        // 23 树分裂后当前节点的左右孩子变成二节点, 所以为黑色
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 判断节点 node 的颜色是否为红色 (如果节点为 null, 则为黑色)
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

}
