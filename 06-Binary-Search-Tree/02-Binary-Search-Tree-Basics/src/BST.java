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

        if (root == null) {
            // 根节点需要单独处理
            root = new Node(e);
        } else {
            // 如果有根节点, 就向以根节点开始的二分搜索树中插入元素 e (递归算法)
            add(root, e);
        }
    }

    // 向以 node 为根的二分搜索树中插入元素 e (递归算法)
    private void add(Node node, E e) {

        // 方式一

        // 递归终止
        if (e.compareTo(node.e) == 0) {
            // 如果传入值等于当前节点的元素值, 则结束递归调用 (当前定义的二分搜索数不能有重复元素)
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            // 如果传入值小于当前节点的元素值, 且当前节点的左孩子为 null, 则将节点插入到左孩子的位置, 结束递归调用
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            // 如果传入值大于当前节点的元素值, 且当前节点的右孩子为 null, 则将节点插入到右孩子的位置, 结束递归调用
            node.right = new Node(e);
            size++;
            return;
        }

        // 递归调用
        if (e.compareTo(node.e) < 0) {
            // 如果传入值小于当前节点的元素值, 且当前节点的左孩子不为 null, 则继续调用 add 方法 (向以 node.left (左孩子) 为根的二分搜索树中插入元素 e)
            add(node.left, e);
        } else { // e.compareTo(node.e) > 0
            // 如果传入值大于当前节点的元素值, 且当前节点的右孩子不为 null, 则继续调用 add 方法 (向以 node.right (右孩子) 为根的二分搜索树中插入元素 e)
            add(node.right, e);
        }

        /* 方式二 (待测试)
        if (e.compareTo(node.e) < 0) {
            if (node.left != null) {
                add(node.left, e);
            } else {
                node.left = new Node(e);
                size++;
            }
        } else if (e.compareTo(node.e) > 0) {
            if (node.right != null) {
                add(node.right, e);
            } else {
                node.right = new Node(e);
                size++;
            }
        }*/
    }

}
