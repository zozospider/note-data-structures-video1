public class BST<E extends Comparable<E>> {

    // 二分搜索树中的节点
    private class Node {

        // 元素值
        E e;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;

    // 当前二分搜索树中的元素个数
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    // 获取树中的元素个数
    public int getSize() {
        return size;
    }

    // 返回树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 二分搜索树中是否包含元素 e
    public boolean contains(E e) {

        // 返回以 root 为根节点的二分搜索树中是否包含元素 e
        return contains(root, e);
    }

    // 向二分搜索树中添加元素 e
    public void add(E e) {

        // 在以 root 为根节点的二分搜索树中添加一个元素
        root = add(root, e);
    }

    // 我们想在以 node 为根节点的二分搜索树中添加一个元素
    // 可以在以 node -> left 为根节点的二分搜索树中添加元素 (规模更小的同等问题)
    // 或者在以 node -> right 为根节点的二分搜索树中添加元素 (规模更小的同等问题)

    // 此方法的宏观语义 (重点关注):
    // 向以 node 为根的二分搜索树中插入元素 e, 并返回该二分搜索树的根
    // 如果 node 为 null, 则插入元素 e 到根位置, 然后返回根
    // 如果 node 不为 null, 则插入元素 e 到 node 的左孩子或右孩子

    // 情况 1:
    // node=null  -> return new Node(e);
    //   null     ->  node (returned)
    // null null    null null

    // 情况 2:
    // node!=null -> return add(node.right, e);
    //   node     ->  node
    // left null   left null (return add(null, e) = 情况 1) -> (return add(right, e) = 情况 2 / 3)

    // 情况 3:
    // node!=null -> return add(node.right, e);
    //   node     ->  node
    // left right   left right (return add(right, e) = 情况 2 / 3)
    private Node add(Node node, E e) {

        // 递归终止
        // 当前传入的根节点为 null, 则返回当前新创建的根节点
        if (node == null) {
            size++;
            return new Node(e);
        }

        // 递归调用 (更小的同等问题)
        // 添加元素 e 到当前节点的左孩子 / 右孩子 (子树) 中
        if (e.compareTo(node.e) < 0) {
            // 如果要添加的元素 e 小于当前节点的元素 e, 则添加到当前节点的左孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点 (node.left) 作为当前 node 的新的左孩子
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // 如果要添加的元素 e 大于当前节点的元素 e, 则添加到当前节点的右孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点 (node.right) 作为当前 node 的新的右孩子
            node.right = add(node.right, e);
        }

        // 返回当前根节点
        return node;
    }

    // 此方法的宏观语义 (重点关注):
    // 判断以 node 为根的二分搜索树中是否包含元素 e
    private boolean contains(Node node, E e) {

        // 递归终止
        // node 为 null, 则不包含
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            // 递归终止
            // 如果元素 e 等于 node 的元素值, 则包含
            return true;
        } else if (e.compareTo(node.e) < 0) {
            // 递归调用 (规模更小的同等问题)
            // 如果元素 e 在 node 的左孩子中, 则返回以 node 的左孩子为根的二分搜索树中是否包含元素 e
            return contains(node.left, e);
        } else { // e.compareTo(node.e) > 0
            // 递归调用 (规模更小的同等问题)
            // 如果元素 e 在 node 的右孩子中, 则返回以 node 的右孩子为根的二分搜索树中是否包含元素 e
            return contains(node.right, e);
        }
    }

}
