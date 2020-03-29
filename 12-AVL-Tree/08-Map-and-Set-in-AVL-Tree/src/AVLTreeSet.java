public class AVLTreeSet<E extends Comparable<E>> implements Set<E> {

    // 内部用 AVL 存储数据
    private AVLTree<E, Object> avlTree;

    public AVLTreeSet() {
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
    public boolean contains(E e) {
        return avlTree.contains(e);
    }

    @Override
    public void add(E e) {
        avlTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        avlTree.remove(e);
    }

}
