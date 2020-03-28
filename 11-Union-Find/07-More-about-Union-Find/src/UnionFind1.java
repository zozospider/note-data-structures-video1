public class UnionFind1 implements UF {

    // 用数组存储当前索引对应的集合编号
    private int[] ids;

    // 示例:
    // 0 (索引 0) -> 0 (集合编号 0)
    // 1 (索引 1) -> 1 (集合编号 1)
    // 2 (索引 2) -> 2 (集合编号 2)
    // 3 (索引 3) -> 3 (集合编号 3)
    public UnionFind1(int size) {

        // 初始化的时候, 所有元素值 (索引对应的集合编号) 都为索引值
        ids = new int[size];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return ids.length;
    }

    // 返回两个元素是否连接 (是否属于同一个集合)
    // 时间复杂度: O(1)

    // 示例:
    // isConnected(0, 3) = (find(0) == find(3)) = (0 == 2) = false
    // isConnected(1, 3) = (find(1) == find(3)) = (2 == 2) = true
    @Override
    public boolean isConnected(int p, int q) {

        // 返回两个元素的索引对应的集合编号是否相等即可
        return find(p) == find(q);
    }

    // 将两个元素进行合并 (合并到同一个集合)
    // 时间复杂度: O(n)

    // 示例:
    // 调用前:
    // 0 (索引 0) -> 0 (集合编号 0)
    // 1 (索引 1) -> 1 (集合编号 1)
    // 2 (索引 2) -> 2 (集合编号 2)
    // 3 (索引 3) -> 3 (集合编号 3)

    // 调用:
    // unionElements(1, 3): 将索引 3 对应的集合编号改成 1

    // 调用后:
    // 0 (索引 0) -> 0 (集合编号 0)
    // 1 (索引 1) -> 1 (集合编号 1)
    // 2 (索引 2) -> 2 (集合编号 2)
    // 3 (索引 3) -> 1 (集合编号 1)

    // 调用:
    // unionElements(2, 3): 将索引 1, 3 对应的集合编号改成 2

    // 调用后:
    // 0 (索引 0) -> 0 (集合编号 0)
    // 1 (索引 1) -> 2 (集合编号 2)
    // 2 (索引 2) -> 2 (集合编号 2)
    // 3 (索引 3) -> 2 (集合编号 2)
    @Override
    public void unionElements(int p, int q) {

        // 查找 p 和 q 对应的集合编号
        int pUnionId = find(p);
        int qUnionId = find(q);

        // 如果集合编号相等则说明两个元素在同一个集合中, 不做任何处理
        // 否则需要将当前并查集中所有等于 qUnionId 的集合编号改成 pUnionId

        if (pUnionId != qUnionId) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == qUnionId) {
                    ids[i] = pUnionId;
                }
            }
        }
    }

    // 查找索引为 i 的元素所对应的集合编号
    private int find(int i) {

        if (i < 0 || i >= ids.length) {
            throw new IllegalArgumentException("i is out of bound.");
        }

        return ids[i];
    }

}
