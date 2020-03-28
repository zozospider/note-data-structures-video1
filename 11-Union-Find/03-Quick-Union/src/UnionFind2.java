public class UnionFind2 implements UF {

    // 用数组存储树中当前节点对应的父节点的集合编号 (建议画图帮助理解)
    private int[] parents;

    public UnionFind2(int size) {

        // 初始化的时候, 所有节点的父节点值 (索引的父节点对应的集合编号) 都指向自己, 所以其值为当前节点的索引值
        parents = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
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
        // 否则需要将 qUnionId 的集合编号改成 pUnionId (p 的根节点的父节点指向 q 的根节点, 即 p 和 q 最终同属于以 pUnionId 为根的树)

        if (pUnionId != qUnionId) {
            parents[qUnionId] = pUnionId;
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
