public class SegmentTree<E> {

    // 存储原始数据的数组
    private E[] data;

    // 存储原始数据对应的线段树结构 (满二叉树) 的数组表示, 所需数组的最大长度为原始数据的 4 倍 (建议画图帮助理解)
    private E[] tree;

    // 合并元素的接口
    private Merger<E> merger;

    // 传入一个数组, 构造当前线段树
    public SegmentTree(E[] arr, Merger<E> merger) {

        // 初始化 data 和 tree

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];

        // 构建线段树

        this.merger = merger;
        // 在线段树 tree 中的 0 索引位置创建表示 (区间 / 合并元素) [0 ... (data.length - 1)] 的线段树
        buildTree(0, 0, (data.length - 1));
    }

    // 在线段树 tree 中的 treeIndex 索引位置创建表示 (区间 / 合并元素) [segmentBeginIndex ... segmentEndIndex] 的线段树 (包括递归创建其子线段树) (建议画图帮助理解)
    // 注: 用 E 表示当前节点 (区间 / 合并元素)
    // treeIndex: 当前 E 在线段树 tree 中的索引位置
    // segmentBeginIndex: 当前 E 的区间开始位置在 data 中的索引位置
    // segmentEndIndex: 当前 E 的区间结束位置在 data 中的索引位置
    private void buildTree(int treeIndex,
                           int segmentBeginIndex, int segmentEndIndex) {

        // 递归终止
        // 如果当前 E 的开始索引和结束索引相等, 则说明已经到了线段树 tree 的最下层, 对 E 进行赋值
        if (segmentBeginIndex == segmentEndIndex) {
            tree[treeIndex] = data[segmentBeginIndex];
            return;
        }

        // 1. 确定左右子树在 tree 中的索引 (用于递归调用)
        int treeLeftIndex = leftChildIndex(treeIndex);
        int treeRightIndex = rightChildIndex(treeIndex);

        // 2. 确定左右子树的开始位置和结束位置在 data 中的索引位置 (用于递归调用)
        // 求出中间位置即可:
        // a. 求出中间位置后, 左子树区间在 data 中的索引位置为: [segmentBeginIndex - segmentMiddleIndex]
        // b. 求出中间位置后, 右子树区间在 data 中的索引位置为: [(segmentMiddleIndex + 1), segmentEndIndex]
        // int segmentMiddleIndex = (segmentEndIndex + segmentBeginIndex) / 2;
        int segmentMiddleIndex = segmentBeginIndex + (segmentEndIndex - segmentBeginIndex) / 2;

        // 3. 递归调用 (左右子树)
        // 在线段树 tree 中的 treeLeftIndex (treeIndex 的左孩子) 的索引位置创建表示区间 [segmentBeginIndex ... segmentMiddleIndex] 的线段树
        // 在线段树 tree 中的 treeRightIndex (treeIndex 的右孩子) 的索引位置创建表示区间 [(segmentMiddleIndex + 1) ... segmentEndIndex] 的线段树
        buildTree(treeLeftIndex,
                segmentBeginIndex, segmentMiddleIndex);
        buildTree(treeRightIndex,
                (segmentMiddleIndex + 1), segmentEndIndex);

        // 4. 求出当前 E 的值 (因为当前值依赖子树值, 所以需要先执行第 1, 2, 3 步, 再执行第 4 步)
        // 在线段树 tree 中, 当前 E 的值等于合并其左右孩子 E 后的值
        tree[treeIndex] = merger.merge(tree[leftChildIndex(treeIndex)], tree[rightChildIndex(treeIndex)]);
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

    // 返回 tree (完全二叉树 / 满二叉树) 的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的左孩子节点的索引
    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    // 返回 tree (完全二叉树 / 满二叉树) 的数组表示中 (从索引 0 开始表示第一个节点), 一个索引所表示的元素的右孩子节点的索引
    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SegmentTree{");
        builder.append("tree=[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                builder.append(tree[i]);
            } else {
                builder.append("null");
            }
            if (i != (tree.length - 1)) {
                builder.append(", ");
            }
        }
        builder.append("]");
        builder.append("}");
        return builder.toString();
    }

}

