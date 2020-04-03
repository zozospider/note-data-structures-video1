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

    // 通过一个元素的 key 计算出该元素在哈希表中对应的数组的索引值
    private int hash(K key) {

        // Integer.MAX_VALUE = 2147483647 = 0b1111111111111111111111111111111 = 0x7fffffff
        // key 的 hashCode 值和 Integer.MAX_VALUE 进行按位与运算, 确保为正数
        // 然后对 M 取模, 计算出的结果就是在数组中的索引值
        return (key.hashCode() & 0x7fffffff) % M;
    }

}
