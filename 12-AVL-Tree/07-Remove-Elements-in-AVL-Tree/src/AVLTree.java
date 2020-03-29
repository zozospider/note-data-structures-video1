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

        // 进行平衡维护
        node = doBalance(node);

        // 返回当前根节点
        return node;
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

    // 将 key 从 AVL 中删除 (从二分搜索树中删除元素为 (key - value) 的节点)
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    public V remove(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则删除 Node 并返回 Node 的 value
        Node node = getNode(root, key);

        if (node == null) {
            return null;
        }

        // 删除以 root 为根的二分搜索树中 key 的节点, 返回删除节点后新的二分搜索树的根
        // 将新的根节点赋值到 root 引用
        root = remove(root, key);
        return node.value;
    }

    // 删除以 node 为根的二分搜索树中 key 的节点, 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {

        // 递归终止
        if (node == null) {
            return null;
        }

        // 递归终止
        if (key.compareTo(node.key) == 0) {

            // 1. 待删除节点的左孩子为 null (如果此时左右右孩子都为 null, 逻辑也兼容)
            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为右孩子, 即表示将原右孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return doBalance(rightNode);
            }
            // 2. 待删除节点的右孩子为 null
            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为左孩子, 即表示将原左孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return doBalance(leftNode);
            }

            // 3. 待删除节点的左右孩子都不为 null

            // 方式一 (更直观的实现方式, 操作不同节点的引用来改变树结构)
            // a. 后继节点策略的实现方式
            // 找到比待删除节点大的最小节点 (即待删除节点右子树的最小节点) (后继节点), 用这个节点顶替待删除节点的位置

            // 找到比待删除节点大的最小节点 (即待删除节点右子树的最小节点) (后继节点)
            Node successor = minimum(node.right);

            // 用这个节点顶替待删除节点的位置 (顶替右孩子)
            // 删除掉以 node.right 为根的二分搜索树中的最小节点 (successor), 返回删除节点后新的二分搜索树的根
            // 将此新的二分搜索树的根作为后继节点的右孩子 (即用这个后继节点顶替了待删除节点的位置)
            successor.right = remove(node.right, successor.key);

            // remove(node.right, successor.key) 逻辑删除了子树中的最小节点, 并修改了成员变量 size 的值 (size--)
            // 但 remove(node.right, successor.key) 执行后, 并没有对当前树的数量产生影响 (被返回的删除节点继续挂接在原树上), 因此理论上需要还原 size 的值 (size++)
            // size++;

            // 用这个节点顶替待删除节点的位置 (顶替左孩子)
            successor.left = node.left;

            // 到此为止, 因为 node 节点已被删除 (被 successor 节点顶替), 因此理论上需要修改成员变量 size 的值 (size--)
            // size--;

            // 返回删除节点后新的二分搜索树的根 (进行平衡维护: 调整以 successor 为根的二叉树的结构, 以满足平衡二叉树性质)
            return doBalance(successor);
        }

        // 递归调用
        if (key.compareTo(node.key) < 0) {

            // 如果要删除的元素 key 小于当前节点的元素, 则从当前节点的左孩子 (子树) 中删除
            // 以 node.left 为根节点, 删除元素 key, 将返回的根节点作为当前 node 的新的左孩子
            node.left = remove(node.left, key);

        } else if (key.compareTo(node.key) > 0) {

            // 如果要删除的元素 key 大于当前节点的元素, 则从当前节点的右孩子 (子树) 中删除
            // 以 node.right 为根节点, 删除元素 key, 将返回的根节点作为当前 node 的新的右孩子
            node.right = remove(node.right, key);
        }

        // 返回当前根节点 (进行平衡维护: 调整以 node 为根的二叉树的结构, 以满足平衡二叉树性质)
        return doBalance(node);
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        // 中序遍历当前树, 求出所有元素的 key, 如果所有 key 都是顺序的, 则说明当前树是二分搜索树, 否则不是二分搜索树
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) < 0) {
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

    // 返回以 node 为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {

        // 递归终止
        if (node.left == null) {
            return node;
        }

        // 递归调用
        return minimum(node.left);
    }

    // 获得以节点 node 为根的树的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 获得以节点 node 为根的树的左右子树中的最大高度
    private int getMaxChildHeight(Node node) {
        return Math.max(getHeight(node.left), getHeight(node.right));
    }

    // 获得节点 node 的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 获得节点 node 的平衡因子的绝对值
    private int getAbsBalanceFactor(Node node) {
        return Math.abs(getBalanceFactor(node));
    }

    // 对节点 y 进行向右旋转操作, 返回旋转后新的根节点 x
    //        y
    //       / \
    //      x  T4   向右旋转 (y)           x
    //     / \     - - - - - - - ->     /  \
    //    z  T3                        z    y
    //   / \                          / \  / \
    //  T1 T2                        T1 T2 T3 T4
    private Node rightRotate(Node y) {

        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新 height
        y.height = getMaxChildHeight(y) + 1;
        x.height = getMaxChildHeight(x) + 1;

        return x;
    }

    // 对节点 y 进行向左旋转操作, 返回旋转后新的根节点 x
    //    y
    //   / \
    //  T1  x        向左旋转 (y)          x
    //     / \     - - - - - - - ->     /  \
    //    T2  z                        y    z
    //       / \                      / \  / \
    //      T3 T4                    T1 T2 T3 T4
    private Node leftRotate(Node y) {

        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转
        x.left = y;
        y.right = T2;

        // 更新 height
        y.height = getMaxChildHeight(y) + 1;
        x.height = getMaxChildHeight(x) + 1;

        return x;
    }

    // 平衡维护: 调整以 node 为根的二叉树的结构, 以满足平衡二叉树性质
    private Node doBalance(Node node) {

        // 获取 node 的平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 以当前 node 为根的树有以下几种不同的情况:

        // LL
        // 左子树比右子树高, 且左孩子的左子树比右子树高, 即当前 node 的左孩子的左孩子导致树不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {

            // 对 node 进行右旋转
            node = rightRotate(node);
        }

        // RR
        // 右子树比左子树高, 且右孩子的右子树比左子树高, 即当前 node 的右孩子的右孩子导致树不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {

            // 对 node 进行左旋转
            node = leftRotate(node);
        }

        // LR
        // 左子树比右子树高, 且左孩子的右子树比左子树高, 即当前 node 的左孩子的右孩子导致树不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {

            // 对 node 的左孩子进行左旋转
            node.left = leftRotate(node.left);

            // 对 node 进行右旋转
            node = rightRotate(node);
        }

        // RL
        // 右子树比左子树高, 且右孩子的左子树比右子树高, 即当前 node 的右孩子的左孩子导致树不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {

            // 对 node 的右孩子进行右旋转
            node.right = rightRotate(node.right);

            // 对 node 进行左旋转
            node = leftRotate(node);
        }

        return node;
    }

}
