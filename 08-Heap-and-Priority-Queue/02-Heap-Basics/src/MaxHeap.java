public class MaxHeap<E extends Comparable<E>> {

    // 内部用动态数组存储数据
    private Array array;

    public MaxHeap(int capacity) {
        array = new Array(capacity);
    }

    public MaxHeap() {
        array = new Array();
    }

    // 获取堆中的元素个数
    public int getSize() {
        return array.getSize();
    }

    // 返回堆是否为空
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 返回完全二叉树的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的父亲节点的索引
    private int parentIndex(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的左孩子节点的索引
    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的右孩子节点的索引
    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

}
