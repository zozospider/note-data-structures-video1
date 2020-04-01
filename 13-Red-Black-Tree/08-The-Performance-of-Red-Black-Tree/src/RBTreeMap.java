public class RBTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 内部用红黑树存储数据
    private RBTree<K, V> rbTree;

    public RBTreeMap() {
        rbTree = new RBTree<>();
    }

    @Override
    public int getSize() {
        return rbTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public boolean contains(K key) {
        return rbTree.contains(key);
    }

    @Override
    public V get(K key) {
        return rbTree.get(key);
    }

    @Override
    public void add(K key, V value) {
        rbTree.add(key, value);
    }

    @Override
    public void set(K key, V value) {
        rbTree.set(key, value);
    }

    // 未实现
    @Override
    public V remove(K key) {
        return null;
    }

}
