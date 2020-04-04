public class AVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 内部用 AVL 存储数据
    private AVLTree<K, V> avlTree;

    public AVLTreeMap() {
        avlTree = new AVLTree<>();
    }

    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }

    @Override
    public boolean contains(K key) {
        return avlTree.contains(key);
    }

    @Override
    public V get(K key) {
        return avlTree.get(key);
    }

    @Override
    public void set(K key, V value) {
        avlTree.set(key, value);
    }

    @Override
    public void add(K key, V value) {
        avlTree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avlTree.remove(key);
    }

}
