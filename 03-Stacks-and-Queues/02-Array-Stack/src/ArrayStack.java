public class ArrayStack<E> implements Stack<E> {

    // 内部用动态数组存储数据
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("ArrayStack: {size = %d, capacity = %d, ", getSize(), array.getCapacity()));

        builder.append("data = [");
        for (int i = 0; i < getSize(); i++) {
            builder.append(array.get(i));
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
