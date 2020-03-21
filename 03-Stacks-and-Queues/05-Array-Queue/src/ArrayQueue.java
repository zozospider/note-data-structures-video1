public class ArrayQueue<E> implements Queue<E> {

    // 内部用动态数组存储数据
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
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

    // 时间复杂度: O(1)
    @Override
    public E getFront() {
        return array.getFirst();
    }

    // 均摊复杂度: O(1)
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    // 时间复杂度: O(n)
    @Override
    public E dequeue() {
        return array.removeFirst();
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
