import java.util.Map;
import java.util.TreeMap;

public class HashTable<K extends Comparable<K>, V> {

    // K 存储在 TreeMap 中时, 需要实现 Comparable 接口

    // 素数容量, 使得所有元素在数组的不同索引中分布更均匀
    private static final int[] CAPACITY = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };

    // 数组中平均每个索引位置的 map 存储的元素个数的上下界
    private static final int UPPER_TOLERANCE = 10;
    private static final int LOWER_TOLERANCE = 2;

    private int capacityIndex;

    // 整个哈希表是多个 map 映射组成的数组, 数组的每个索引对应的元素是一个 map 映射
    private Map<K, V>[] maps;

    // 当前哈希表中的元素个数
    private int size;

    public HashTable() {

        // 初始化容量为第一个素数
        capacityIndex = 0;

        // 初始化数组中的每个索引对应的元素为空的 map 映射对象
        maps = new TreeMap[CAPACITY[capacityIndex]];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new TreeMap<>();
        }

        this.size = 0;
    }

    // 当前哈希表中的元素个数
    public int getSize() {
        return size;
    }

    // 哈希表是否为空
    public boolean isEmpty() {
        return size == 0;
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

            // 如果哈希表中的元素个数大于等于 (数组长度 * 上界), 说明平均每个 map 存储的元素个数超过了上届, 将数组的容量扩容为下一个素数的大小
            // 增加 ((capacityIndex + 1) < CAPACITY.length) 的条件判断用于防止超出 CAPACITY 定义的最大素数范围 (数组越界)
            if (size >= UPPER_TOLERANCE * maps.length
                    && (capacityIndex + 1) < CAPACITY.length) {

                resize(CAPACITY[++capacityIndex]);
            }
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

        // 如果哈希表中的元素个数小于 (数组长度 * 下界), 说明平均每个 map 存储的元素个数小于了下届, 将数组的容量缩容为原来的 1/2
        // 增加 ((capacityIndex - 1) >= 0) 的条件判断用于防止超出 CAPACITY 定义的最小素数范围 (数组越界)
        if (size < LOWER_TOLERANCE * maps.length
                && (capacityIndex - 1) >= 0) {

            resize(CAPACITY[--capacityIndex]);
        }

        return value;
    }

    // 通过一个元素的 key 计算出该元素在长度为 M 的数组中的索引值
    private int hash(K key, int M) {

        // Integer.MAX_VALUE = 2147483647 = 0b1111111111111111111111111111111 = 0x7fffffff
        // key 的 hashCode 值和 Integer.MAX_VALUE 进行按位与运算, 确保为正数
        // 然后对 M 取模, 计算出的结果就是在数组中的索引值
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // 通过一个元素的 key 计算出该元素在当前哈希表中的数组的索引值
    private int hash(K key) {
        return hash(key, maps.length);
    }

    // 将 maps 数组的容量变成 newCapacity 大小
    private void resize(int newCapacity) {

        Map<K, V>[] newMaps = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newMaps[i] = new TreeMap<>();
        }

        // 将 maps 旧数组中的每个旧索引位置的 oldMap 旧映射中的所有元素 (key - value) 添加到 newMaps 新数组中的每个新索引位置的 newMap 新映射中
        for (Map<K, V> oldMap : maps) {

            // 遍历 maps 中 oldIndex 索引位置的 oldMap 中的所有元素 (key - value), 将元素加入到 newMaps 中 newIndex 索引位置的 newMap 中
            for (Map.Entry<K, V> kvEntry : oldMap.entrySet()) {

                K key = kvEntry.getKey();
                V value = kvEntry.getValue();

                // 计算出 key 在 newMaps 数组中的新索引值, 并获取 newMaps 数组中该新索引的 newMap 对象
                int newIndex = hash(key, newMaps.length);
                Map<K, V> newMap = newMaps[newIndex];

                newMap.put(key, value);
            }
        }

        // 将原 maps 数组指向该 newMaps, 即表示 maps 转换成功 (newMaps 为栈内存变量, 会自动回收)
        maps = newMaps;
    }

}
