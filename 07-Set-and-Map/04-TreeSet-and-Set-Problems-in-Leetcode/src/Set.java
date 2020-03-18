public interface Set<E> {

    // 集合元素的大小
    int getSize();

    // 集合是否为空
    boolean isEmpty();

    // 集合中是否包含元素 e
    boolean contains(E e);

    // 将元素 e 添加到集合中
    void add(E e);

    // 将元素 e 从集合中删除
    void remove(E e);

}
