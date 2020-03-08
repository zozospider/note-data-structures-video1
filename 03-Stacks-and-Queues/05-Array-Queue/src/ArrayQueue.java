import com.sun.javafx.binding.StringFormatter;

public class ArrayQueue<E> implements Queue<E> {

    // 内部用动态数组存储数据
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
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
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
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

        builder.append("ArrayQueue: {");
        builder.append(String.format("size = %d, capacity = %d, ", getSize(), getCapacity()));

        builder.append("data = {front [");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(get(i));
            if (i != (getSize() - 1)) {
                builder.append(", ");
            }
        }
        builder.append("] tail}}");

        return builder.toString();

        /*return "ArrayQueue{" +
                "array=" + array +
                '}';*/
    }

}
