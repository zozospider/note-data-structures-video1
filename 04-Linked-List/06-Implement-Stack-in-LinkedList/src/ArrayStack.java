public class ArrayStack<E> implements Stack<E> {

    // 内部用动态数组存储数据
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return array.getSize();
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 均摊复杂度: O(1)
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    // 均摊复杂度: O(1)
    @Override
    public E pop() {
        return array.removeLast();
    }

    // 时间复杂度: O(1)
    @Override
    public E peek() {
        return array.getLast();
    }

    private int getCapacity() {
        return array.getCapacity();
    }

    private E get(int index) {
        return array.get(index);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("ArrayStack: {size = %d, capacity = %d, ", getSize(), getCapacity()));

        builder.append("data = [");
        for (int i = 0; i < getSize(); i++) {
            builder.append(get(i));
            if (i != (getSize() - 1)) {
                builder.append(", ");
            }
        }
        builder.append("] top}");

        return builder.toString();

        /*return "ArrayStack{" +
                "array=" + array +
                '}';*/
    }

}
