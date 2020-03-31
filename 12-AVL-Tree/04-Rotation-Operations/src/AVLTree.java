import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {

    // AVL 的节点
    private class Node {

        // key
        K key;

        // value
        V value;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        // 以当前节点为根的树的高度 (如果没有子节点则为 1)
        int height;

        Node (K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }
    }

    // 根节点
    private Node root;

    // 当前 AVL 中的元素个数 (当前二分搜索树中的元素个数)
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    // AVL 元素的大小 (获取树中的元素个数)
    // 时间复杂度: O(1)
    public int getSize() {
        return size;
    }

    // AVL 是否为空 (返回树是否为空)
    // 时间复杂度: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // AVL 中是否包含 key (二分搜索树中是否包含元素 (key - value))
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    public boolean contains(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 不为 null 则存在, 否则不存在
        return getNode(root, key) != null;
    }

    // 获取 AVL 中 key 对应的 value
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    public V get(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则返回 Node 的 value
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 修改 AVL 中 key 对应的 value
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
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

    // 将元素 (key - value) 添加到 AVL 中 (向二分搜索树中添加元素 (key - value)) (如果 key 已存在, 则修改 key 对应的 value)
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
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

        if (key.compareTo(node.key) == 0) {

            // 递归终止
            // 如果 key 等于 node 的 key, 则找到 Node, 修改 Node 的 value
            node.value = value;

        } else if (key.compareTo(node.key) < 0) {

            // 如果要添加的元素 key 小于当前节点的元素, 则添加到当前节点的左孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 (key - value), 将返回的根节点作为当前 node 的新的左孩子
            node.left = add(node.left, key, value);

        } else { // key.compareTo(node.key) > 0

            // 如果要添加的元素 key 小于当前节点的元素, 则添加到当前节点的右孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 (key - value), 将返回的根节点作为当前 node 的新的右孩子
            node.right = add(node.right, key, value);
        }

        // 更新 height 为左右子树中的最大高度值加 1
        node.height = getMaxChildHeight(node) + 1;

        // 判断平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 && getBalanceFactor(node.left) <= 0) {

        }

        // 返回当前根节点
        return node;
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        // 中序遍历当前树, 求出所有元素的 key, 如果所有 key 都是顺序的, 则说明当前树是二分搜索树, 否则不是二分搜索树
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalancedTree() {
        return isBalancedTree(root);
    }

    // 判断以 node 为根的二叉树是否是一棵平衡二叉树
    private boolean isBalancedTree(Node node) {

        // 递归终止
        // 节点为 null, 满足平衡二叉树条件
        if (node == null) {
            return true;
        }

        // 递归终止
        // 平衡因子绝对值大于 1, 不满足平衡二叉树条件
        if (getAbsBalanceFactor(node) > 1) {
            return false;
        }

        // 否则说明当前节点满足平衡二叉树条件, 需要判断其子节点是否也满足平衡二叉树条件

        // 递归调用
        // 求出以 node 的左孩子为根的二叉树是否是一棵平衡二叉树
        // 求出以 node 的右孩子为根的二叉树是否是一棵平衡二叉树
        boolean leftChildBalanced = isBalancedTree(node.left);
        boolean rightChildBalanced = isBalancedTree(node.right);

        // 当 node 的左右孩子都为平衡二叉树时, 以 node 为根的二叉树才是一棵平衡二叉树
        // 当 node 的左右孩子有一个不是平衡二叉树时, 以 node 为根的二叉树就不是一棵平衡二叉树
        return leftChildBalanced && rightChildBalanced;
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

    // 获得节点 node 的平衡因子的绝对值
    private int getAbsBalanceFactor(Node node) {
        return Math.abs(getBalanceFactor(node));
    }

    // 获得节点 node 的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 获得以节点 node 为根的树的左右子树中的最大高度
    private int getMaxChildHeight(Node node) {
        return Math.max(getHeight(node.left), getHeight(node.right));
    }

    // 获得以节点 node 为根的树的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

}
