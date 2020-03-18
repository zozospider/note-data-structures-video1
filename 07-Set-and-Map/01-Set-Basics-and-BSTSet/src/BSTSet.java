public class BSTSet<E extends Comparable<E>> implements Set<E> {

    // 内部用二分搜索树存储数据
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return bst.getSize();
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    // 平均复杂度: O(h) = O(log n)
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    // 平均复杂度: O(h) = O(log n)
    @Override
    public void add(E e) {
        bst.add(e);
    }

    // 平均复杂度: O(h) = O(log n)
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

}
