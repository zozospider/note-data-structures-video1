public class SegmentTree<E> {

    // 存储所有元素的数组
    private E[] data;

    // 存储原始数据对应的线段树结构 (满二叉树) 的数组表示, 所需数组的最大长度为原始数据的 4 倍 (建议画图帮助理解)
    private E[] tree;

    // 传入一个数组, 构造当前线段树
    public SegmentTree(E[] arr) {

        // 初始化 data 和 tree
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
    }

    // 获取元素个数
    public int getSize() {
        return data.length;
    }

    // 获取 index 索引位置的元素
    public E get(int index) {

        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        // 返回 data 中 index 索引位置的元素
        return data[index];
    }

    // 返回完全二叉树的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的左孩子节点的索引
    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的右孩子节点的索引
    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

}

