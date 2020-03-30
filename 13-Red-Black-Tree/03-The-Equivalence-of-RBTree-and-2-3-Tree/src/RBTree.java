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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断节点 node 的颜色是否为红色 (如果节点为 null, 则为黑色)
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

}
