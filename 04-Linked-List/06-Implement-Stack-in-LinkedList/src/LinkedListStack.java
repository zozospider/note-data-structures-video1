public class LinkedListStack<E> implements Stack<E> {

    // 内部用链表存储数据
    private LinkedList<E> linkedList;

    public LinkedListStack() {
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

    // 时间复杂度: O(1)
    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    // 时间复杂度: O(1)
    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    // 时间复杂度: O(1)
    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        return "LinkedListStack{" +
                "linkedList=" + linkedList +
                '}';
    }

}
