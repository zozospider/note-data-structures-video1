public class MaxHeap<E extends Comparable<E>> {

    // 内部用原始数据对应的完全二叉树结构的数组来存储数据
    private Array<E> array;

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public MaxHeap() {
        array = new Array<>();
    }

    // 通过任意数组初始化堆 (Heapify 方式: 建议画图帮助理解执行逻辑)
    // 时间复杂度 O(n) (证明方式较为复杂)
    public MaxHeap(E[] arr) {

        array = new Array<>(arr);

        // 从完全二叉树的最后一个节点的父节点开始, 依次往前遍历, 直到第顶部节点
        // 每次循环都对当前索引执行下沉操作
        int startIndex = parentIndex(array.getSize() - 1);
        for (int i = startIndex; i > 0; i--) {
            siftDown(i);
        }
    }

    // 获取堆中的元素个数
    // 时间复杂度: O(1)
    public int getSize() {
        return array.getSize();
    }

    // 返回堆是否为空
    // 时间复杂度: O(1)
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 查询堆中的最大元素
    // 时间复杂度: O(1)
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return array.get(0);
    }

    // 向堆中添加元素
    // 时间复杂度: O(log n)
    public void add(E e) {

        // 先将元素 e 添加到数组的最后位置 (即添加到完全二叉树的最下层的最右边的叶子节点位置)
        array.addLast(e);

        // 上浮操作: 从数组的最后节 (完全二叉树的最下层的最右边的叶子节点) 开始, 进行上浮操作, 直到满足堆结构的规则
        siftUp(array.getSize() - 1);
    }

    // 取出堆中最大元素
    // 时间复杂度: O(log n)
    public E extractMax() {

        E max = findMax();

        // 将数组的第一个元素和最后一个元素交换, 并移除最后一个元素
        array.swap(0, array.getSize() - 1);
        array.removeLast();

        // 下沉操作: 从数组的第一个 (完全二叉树的最上层的节点) 开始, 进行下沉操作, 直到满足堆结构的规则
        siftDown(0);

        return max;
    }

    // 取出堆中的最大元素, 并且替换成元素 e (方式一: 先替换最大元素, 再执行下沉操作, 建议画图帮助理解执行逻辑)
    // 时间复杂度: O(log n)
    public E replace(E e) {

        // 查询堆中的最大元素
        // 时间复杂度: O(1)
        E max = findMax();

        // 将元素 e 设置成堆的最大值后, 执行下沉操作, 确保堆结构的正确性
        array.set(0, e);
        // 时间复杂度: O(log n)
        siftDown(0);

        return max;
    }

    // 取出堆中的最大元素, 并且替换成元素 e (方式二: 先取出最大元素, 再添加当前元素)
    // 时间复杂度: 2 * O(log n)
    public E replace2(E e) {

        // 取出堆中的最大元素
        // 时间复杂度: O(log n)
        E max = extractMax();

        // 将元素 e 添加到堆中
        // 时间复杂度: O(log n)
        add(e);

        return max;
    }

    // 上浮操作: 将 index 所在的节点不断上浮, 直到满足堆结构的规则 (即 index 所在的节点值小于等于 index 所在节点的父节点值)
    private void siftUp(int index) {

        // 如果 index 大于 0, 说明节点存在, 继续上浮循环
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

        // 如果 index 左孩子小于数组长度, 说明节点存在, 继续下沉循环
        while (leftChildIndex(index) < array.getSize()) {

            // 先求出当前 index 节点的左右孩子节点中更大值的孩子索引
            int childIndex = getLargerChildIndex(leftChildIndex(index), rightChildIndex(index));

            if (array.get(index).compareTo(array.get(childIndex)) >= 0) {

                // 如果当前 index 节点值大于等于更大的孩子节点值, 则表示已经符合堆结构, 结束下沉循环
                break;

            } else {

                // 否则说明当前 index 节点值小于其更大的孩子节点值 (不符合堆结构)
                // 交换 index 和其更大的孩子节点的元素, 并继续下沉循环

                array.swap(index, childIndex);
                index = childIndex;
            }
        }
    }

    // 获取左右孩子索引对应的节点中, 更大值的孩子索引
    private int getLargerChildIndex(int left, int right) {

        // 如果右孩子存在 (则左孩子一定存在), 且右孩子大于左孩子, 则返回右孩子
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
