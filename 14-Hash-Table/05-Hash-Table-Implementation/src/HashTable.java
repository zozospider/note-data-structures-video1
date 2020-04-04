import java.util.Map;
import java.util.TreeMap;

public class HashTable<K, V> {

    // 整个哈希表是多个 map 映射组成的数组, 数组的每个索引对应的元素是一个 map 映射
    private Map<K, V>[] maps;

    // 数组的大小
    private int M;

    // 当前哈希表中的元素个数
    private int size;

    // 指定数组大小
    public HashTable(int M) {

        this.M = M;
        this.size = 0;

        // 初始化数组中的每个索引对应的元素为空的 map 映射对象
        maps = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            maps[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    // 当前哈希表中的元素个数
    public int getSize() {
        return size;
    }

    // 哈希表中是否包含 key
    public boolean contains(K key) {

        // 计算 key 在哈希表中对应的数组的索引值, 并获取数组中该索引的 map 对象
        int index = hash(key);
        Map<K, V> map = maps[index];

        // 返回 map 中是否包含当前 key
        return map.containsKey(key);
    }

    // 获取哈希表中 key 对应的 value
    public V get(K key) {

        // 计算 key 在哈希表中对应的数组的索引值, 并获取数组中该索引的 map 对象
        int index = hash(key);
        Map<K, V> map = maps[index];

        // 返回 map 中的 key 对应的 value (如果没有则返回 null)
        return map.get(key);
    }

    // 修改哈希表中 key 对应的 value
    public void set(K key, V value) {

        // 计算 key 在哈希表中对应的数组的索引值, 并获取数组中该索引的 map 对象
        int index = hash(key);
        Map<K, V> map = maps[index];

        // 如果 key 不存在, 则抛出异常
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist!");
        }

        // 执行修改
        map.put(key, value);
    }

    // 将元素 (key - value) 添加到哈希表中 (如果 key 已存在, 则修改 key 对应的 value)
    public void add(K key, V value) {

        // 计算 key 在哈希表中对应的数组的索引值, 并获取数组中该索引的 map 对象
        int index = hash(key);
        Map<K, V> map = maps[index];

        // 如果 key 存在, 则修改 key 对应的 value
        // 如果 key 不存在, 则增加 key, value, 并增加 size 值
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
        }
    }

    // 将 key 从哈希表中删除
    public V remove(K key) {

        // 计算 key 在哈希表中对应的数组的索引值, 并获取数组中该索引的 map 对象
        int index = hash(key);
        Map<K, V> map = maps[index];

        // 如果 key 不存在, 则返回 null
        if (!map.containsKey(key)) {
            return null;
        }

        // 如果 key 不存在, 则从 map 中移除 key, 减少 size 值, 返回移除的 value
        V value = map.remove(key);
        size--;
        return value;
    }

    // 通过一个元素的 key 计算出该元素在哈希表中对应的数组的索引值
    private int hash(K key) {

        // Integer.MAX_VALUE = 2147483647 = 0b1111111111111111111111111111111 = 0x7fffffff
        // key 的 hashCode 值和 Integer.MAX_VALUE 进行按位与运算, 确保为正数
        // 然后对 M 取模, 计算出的结果就是在数组中的索引值
        return (key.hashCode() & 0x7fffffff) % M;
    }

}
