public class MaxHeap<E extends Comparable<E>> {

    // 内部用原始数据对应的完全二叉树结构的数组来存储数据
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

    // 查询堆中的最大元素
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return array.get(0);
    }

    // 向堆中添加元素
    public void add(E e) {

        // 先将元素 e 添加到数组的最后位置 (即添加到完全二叉树的最下层的最右边的叶子节点位置)
        array.addLast(e);

        // 上浮操作: 从数组的最后节 (完全二叉树的最下层的最右边的叶子节点) 开始, 进行上浮操作, 直到满足堆结构的规则
        siftUp(array.getSize() - 1);
    }

    // 取出堆中最大元素
    public E extractMax() {

        E max = findMax();

        // 将数组的第一个元素和最后一个元素交换, 并移除最后一个元素
        array.swap(0, array.getSize() - 1);
        array.removeLast();

        // 下沉操作: 从数组的第一个 (完全二叉树的最上层的节点) 开始, 进行下沉操作, 直到满足堆结构的规则
        siftDown(0);

        return max;
    }

    // 上浮操作: 将 index 所在的节点不断上浮, 直到满足堆结构的规则 (即 index 所在的节点值小于等于 index 所在节点的父节点值)
    private void siftUp(int index) {

        // 如果 index 大于 0, 说明节点存在且为非顶部节点, 继续上浮循环
        while (index > 0) {

            // 先求出当前 index 节点的父节点的索引
            int parentIndex = parentIndex(index);

            if (array.get(index).compareTo(array.get(parentIndex)) <= 0) {

                // 如果当前 index 节点值小于等于父节点值, 则表示已经符合堆结构, 结束上浮循环
                break;

            } else {

                // 否则说明当前 index 节点值大于其父节点值 (不符合堆结构)
                // 交换 index 和其父节点的元素, 并继续上浮循环

                array.swap(index, parentIndex);
                index = parentIndex;
            }
        }
    }

    // 下沉操作: 将 index 所在的节点不断下沉, 直到满足堆结构的规则 (即 index 所在的节点值大于等于 index 所在节点的孩子节点值)
    private void siftDown(int index) {

        // 如果 index 左孩子小于数组长度, 说明节点和其左孩子都存在, 继续下沉循环
        while (leftChildIndex(index) < array.getSize()) {

            // 先求出当前 index 节点的左孩子和右孩子中, 值更大的孩子节点的索引
            int largerChildIndex = getLargerChildIndex(index);

            if (array.get(index).compareTo(array.get(largerChildIndex)) >= 0) {

                // 如果当前 index 节点值大于等于更大的孩子节点值, 则表示已经符合堆结构, 结束下沉循环
                break;

            } else {

                // 否则说明当前 index 节点值小于其更大的孩子节点值 (不符合堆结构)
                // 交换 index 和其更大的孩子节点的元素, 并继续下沉循环

                array.swap(index, largerChildIndex);
                index = largerChildIndex;
            }
        }
    }

    // 获取当前节点的左孩子和右孩子中, 值更大的孩子节点的索引
    private int getLargerChildIndex(int index) {

        // 求出左孩子和右孩子 (while 循环条件已确保左孩子一定存在, 但右孩子可能不存在)
        int left = leftChildIndex(index);
        int right = rightChildIndex(index);

        // 如果右孩子存在, 且右孩子大于左孩子, 则返回右孩子
        if (right < array.getSize()
                && array.get(right).compareTo(array.get(left)) >= 0) {
            return right;
        }
        // 否则返回左孩子
        return left;
    }

    // 返回 array (完全二叉树) 的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的父亲节点的索引
    private int parentIndex(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回 array (完全二叉树) 的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的左孩子节点的索引
    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    // 返回 array (完全二叉树) 的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的右孩子节点的索引
    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

}
