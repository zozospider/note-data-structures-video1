public class MaxHeap<E extends Comparable<E>> {

    // 内部用动态数组存储数据
    private Array<E> array;

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public MaxHeap() {
        array = new Array<>();
    }

    // 获取堆中的元素个数
    public int getSize() {
        return array.getSize();
    }

    // 返回堆是否为空
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void add(E e) {

        // 先将元素 e 添加到数组的最后位置 (即添加到完全二叉树的最下层的最右边的叶子节点位置)
        array.addLast(e);

        // 上浮操作: 从数组的最后 (完全二叉树的最下层的最右边的叶子节点) 开始, 进行上浮操作, 直到满足堆结构的规则
        siftUp(array.getSize() - 1);
    }

    // 上浮操作: 将 index 所在的节点不断上浮, 直到满足堆结构的规则 (即 index 所在的节点值小于等于 index 所在节点的父节点值)
    private void siftUp(int index) {

        // 循环, 如果 index 所在节点值大于 index 所在节点的父节点值, 则交换 index 和其父节点两个索引的元素
        while (index > 0
                && array.get(index).compareTo(array.get(parentIndex(index))) > 0) {

            // 交换 index 和其父节点两个索引的元素
            array.swap(index, parentIndex(index));

            // 交换后的新的 index 索引, 用于下次循环
            index = parentIndex(index);
        }
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
