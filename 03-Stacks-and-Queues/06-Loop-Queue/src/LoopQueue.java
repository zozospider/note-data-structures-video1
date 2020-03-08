public class LoopQueue<E> implements Queue<E> {

    // 用于存放元素的数组
    private E[] data;
    // 记录数据的起始索引位置
    private int front;
    // 记录下一个数据的索引位置 (最后一个数据的索引位置 + 1)
    private int tail;
    // 当前队列中的元素个数
    private int size;

    public LoopQueue(int capacity) {
        // 因为循环数组的数据结构会浪费一个位置, 所以需要多一个长度, 以满足用户传入的 capacity 容量需求
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

}
