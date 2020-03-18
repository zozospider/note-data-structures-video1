public interface Map<K, V> {

    int getSize();

    boolean isEmpty();

    boolean contains(K key);

    V get(K key);

    void add(K key, V value);

    void set(K key, V value);

    V remove(K key);

}
