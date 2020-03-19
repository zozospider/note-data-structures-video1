public class BST<E extends Comparable<E>> {

    // 二分搜索树中的节点
    private class Node {

        // 元素值
        E e;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
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

}
