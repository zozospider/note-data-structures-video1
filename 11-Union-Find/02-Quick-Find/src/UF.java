public interface UF {

    // 并查集元素的大小
    int getSize();

    // 返回两个元素是否连接 (是否属于同一个集合)
    boolean isConnected(int p, int q);

    // 将两个元素进行合并
    void unionElements(int p, int q);

}
