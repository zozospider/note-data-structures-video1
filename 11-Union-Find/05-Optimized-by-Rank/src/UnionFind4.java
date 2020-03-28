public class UnionFind4 implements UF {

    // 用数组存储树中当前节点对应的父节点的集合编号 (建议画图帮助理解)
    private int[] parents;

    // 用数组存储以当前节点为根的树的层数
    private int[] ranks;

    public UnionFind4(int size) {

        // 初始化的时候, 所有节点的父节点值 (索引的父节点对应的集合编号) 都指向自己, 所以其值为当前节点的索引值
        // 所有节点都为独立的树, 每个树层树都为 1
        parents = new int[size];
        ranks = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return parents.length;
    }

    // 返回两个节点是否连接 (是否属于同一个集合)
    // 时间复杂度: O(h) (h 为树的高度)
    @Override
    public boolean isConnected(int p, int q) {

        // 返回两个节点的索引对应的集合编号是否相等即可
        return find(p) == find(q);
    }

    // 将两个元素进行合并 (合并到同一个集合)
    // 时间复杂度: O(h) (h 为树的高度)
    @Override
    public void unionElements(int p, int q) {

        // 查找 p 和 q 对应的集合编号 (即当前节点所在的树的根节点的值)
        int pUnionId = find(p);
        int qUnionId = find(q);

        // 如果集合编号相等则说明两个节点在同一个集合中 (在同一个树中), 不做任何处理
        // 否则需要先比较 p 所在的树的层数和 q 所在的树的层数, 将层数小的树的根节点指向层数大的树的根节点

        if (pUnionId != qUnionId) {

            if (ranks[qUnionId] < ranks[pUnionId]) {

                // 如果 q 所在的树的层数小于 p 所在的树的层数, 将 q 的根节点的父节点指向 p 的根节点 (树的层数无需修改, 因为合并后 p 和 q 的层数都没有变化)
                parents[qUnionId] = pUnionId;

            } else if (ranks[qUnionId] > ranks[pUnionId]) {

                // 如果 p 所在的树的层数小于 q 所在的树的层数, 将 p 的根节点的父节点指向 q 的根节点 (树的层数无需修改, 因为合并后 p 和 q 的层数都没有变化)
                parents[pUnionId] = qUnionId;

            } else {

                // 如果 q 所在的树的层数小于 p 所在的树的层数, 将 q 的根节点的父节点指向 p 的根节点, 并将以 p 为根节点的树的层数加 1 (合并后 p 的层数增加了 1)
                parents[qUnionId] = pUnionId;
                ranks[pUnionId] += 1;
            }
        }
    }

    // 查找索引为 i 的节点所对应的集合编号 (即当前节点所在的树的根节点的值)
    private int find(int i) {

        if (i < 0 || i >= parents.length) {
            throw new IllegalArgumentException("i is out of bound.");
        }

        // 不断循环找当前节点 (索引为 i) 的父节点的集合编号 (集合编号为 parents[i]), 直到集合编号等于其某个父节点的索引值 (parents[i] == i), 即为根节点的值
        while (parents[i] != i) {
            i = parents[i];
        }

        // 返回根节点的集合编号 (也就是当前节点的集合编号)
        // return i;
        return parents[i];
    }

}
