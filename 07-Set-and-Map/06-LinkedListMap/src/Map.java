public interface Map<K, V> {

    // 映射元素的大小
    int getSize();

    // 映射是否为空
    boolean isEmpty();

    // 映射中是否包含 key
    boolean contains(K key);

    // 获取映射中 key 对应的 value
    V get(K key);

    // 将元素 key value 添加到映射中 (如果 key 已存在, 则修改 key 对应的 value)
    void add(K key, V value);

    // 修改映射中 key 对应的 value
    void set(K key, V value);

    // 将 key 从映射中删除
    V remove(K key);

}
