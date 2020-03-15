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
        if (e.compareTo(node.e) < 0) {
            // 插入到根节点的左孩子
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点 (node.left) 赋值给 node.left
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // 插入到根节点的右孩子
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点 (node.right) 赋值给 node.right
            node.right = add(node.right, e);
        }

        // 返回当前根节点
        return node;
    }

    // 二分搜索树中是否包含元素 e
    public boolean contains(E e) {

        // 返回以 root 为根节点的二分搜索树中是否包含元素 e
        return contains(root, e);
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

    // 二分搜索树的前序遍历
    public void preOrder() {
        // 前序遍历以 root 为根的二分搜索树
        preOrder(root);
    }

    // 前序遍历以 node 为根的二分搜索树
    private void preOrder(Node node) {

        // 递归终止
        if (node == null) {
            return;
        }

        System.out.println(node.e);

        // 递归调用 (规模更小的同等问题)
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BST{\n");
        BSTToString(root, 0, builder);
        builder.append("}");
        return builder.toString();
    }

    private void BSTToString(Node node, int depth, StringBuilder builder) {
        // 递归终止
        if (node == null) {
            builder.append(toDepthString(depth)).append("null\n");
            return;
        }
        builder.append(toDepthString(depth)).append(node.e).append("\n");
        // 递归调用 (规模更小的同等问题)
        BSTToString(node.left, depth + 1, builder);
        BSTToString(node.right, depth + 1, builder);
    }

    private String toDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("- ");
        }
        return builder.toString();
    }

}
