public class LinkedListSet<E> implements Set<E> {

    // 内部用链表存储数据
    private LinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    // 时间复杂度: O(n)
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    // 时间复杂度: O(n) + O(1) = O(n)
    @Override
    public void add(E e) {

        // 因为集合不能添加重复元素, 所以需要先判断是否已包含该元素
        if (!linkedList.contains(e)) {

            // 在链表的头部添加新元素的时间复杂度为 O(1)
            linkedList.addFirst(e);
        }
    }

    // 时间复杂度: O(n)
    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

}
