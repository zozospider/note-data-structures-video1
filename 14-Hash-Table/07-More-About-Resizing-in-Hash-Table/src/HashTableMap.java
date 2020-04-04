public class HashTableMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 内部用哈希表存储数据
    private HashTable<K, V> hashTable;

    public HashTableMap() {
        hashTable = new HashTable<>();
    }

    @Override
    public int getSize() {
        return hashTable.getSize();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    @Override
    public boolean contains(K key) {
        return hashTable.contains(key);
    }

    @Override
    public V get(K key) {
        return hashTable.get(key);
    }

    @Override
    public void set(K key, V value) {
        hashTable.set(key, value);
    }

    @Override
    public void add(K key, V value) {
        hashTable.add(key, value);
    }

    @Override
    public V remove(K key) {
        return hashTable.remove(key);
    }

}
