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

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return size;
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    // 时间复杂度: O(1)
    @Override
    public E getFront() {

        // 不能从空数组取出元素
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");
        }

        return data[front];
    }

    // 均摊复杂度: O(1)
    @Override
    public void enqueue(E e) {

        // 判断是否需要扩容

        // (tail + 1) 是因为需要多一个空闲位置, 不能填满
        // (tail + 1) % data.length 是因为尾部元素重新回到数组头部时, 需要减去多出来的数组容量部分, 取余后才表示 tail 元素离数组 0 索引位置的绝对距离
        // 当 (tail + 1) % data.length 和 front 指向同一个位置时, 表示数组已经满了

        // 假设: capacity = 7, data.length = 8, front = 2, tail = 9, size = 7
        // 那么: (tail + 1) = 10, (tail + 1) % data.length = 10 % 8 = 2 == front
        // 那么: resize(2 * 7) = resize(14)

        // 注: 此处可考虑省略求余, 因为理论上下面的逻辑可以保证 tail 不会超过 data.length

        if ((tail + 1) % data.length == front) {
            resize(2 * getCapacity());
        }

        // 设置入队元素
        data[tail] = e;

        // 增加 tail
        tail = (tail + 1) % data.length;

        // 元素个数加 1
        size++;
    }

    // 均摊复杂度: O(1)
    @Override
    public E dequeue() {

        // 不能从空数组取出元素
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");
        }

        // 获取队列头部元素
        E e = data[front];

        // 释放 size 索引位置的元素 (loitering objects) 的引用
        // loitering objects != memory leak
        data[front] = null;

        // 增加 front
        front = (front + 1) % data.length;

        // 元素个数减 1
        size--;

        // 判断是否需要缩容
        // 如果数组中的元素个数等于数组长度的 1/4, 说明数组比较空闲, 将数组的容量缩容为原来的 1/2
        // 此处用到 lazy 机制, 即等到剩余 1/4 空间的时候才缩容到 1/2, 防止复杂度的震荡
        // 增加 (getCapacity() / 2 != 0) 的条件判断用于防止当 (getCapacity() == 1) 时 (newCapacity = 1 / 2 = 0) 的情况
        if (size == data.length / 4
                && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return e;
    }

    // 将数组的容量变成 newCapacity 大小
    private void resize(int newCapacity) {

        // 创建一个新的数组 newData
        E[] newData = (E[]) new Object[newCapacity];

        // 将原 data 数组中的每个元素赋值给新数组 newData

        // 因为第一个元素的位置在 front 索引处, 所以在拷贝时, 新元素的索引 i 对应原数组索引 (front + i)
        // 因为 data[(front + i)] 元素可能会超出数组长度, 需要减去多出来的数组容量部分, 取余后才表示当前元素离数组 0 索引位置的绝对距离, 即 data 真正的索引值

        // 假设: capacity = 7, data.length = 8, front = 2, tail = 9, size = 7
        // 那么拷贝数组元素如下:
        // newData[0] = data[(2 + 0) % 8] = data[2]
        // newData[1] = data[(2 + 1) % 8] = data[3]
        // newData[2] = data[(2 + 2) % 8] = data[4]
        // newData[3] = data[(2 + 3) % 8] = data[5]
        // newData[4] = data[(2 + 4) % 8] = data[6]
        // newData[5] = data[(2 + 5) % 8] = data[7]
        // newData[6] = data[(2 + 6) % 8] = data[0]

        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        // 将原 data 数组指向该新数组, 即表示 data 转换成功 (newData 为栈内存变量, 会自动回收)
        data = newData;

        // 拷贝后的数组结构, 从索引 0 开始存储, 依次存储 size 个元素, 直到索引 size
        // 因此 front = 0, tail = size
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("LoopQueue: {size = %d, capacity = %d, front = %d, tail = %d, ", size, getCapacity(), front, tail));

        builder.append("front [");
        for (int i = 0; i < size; i++) {
            builder.append(data[(front + i) % data.length]);
            if ((front + i) % data.length != (tail - 1)) {
                builder.append(", ");
            }
        }
        builder.append("] tail");

        return builder.toString();

        /*return "LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", front=" + front +
                ", tail=" + tail +
                ", size=" + size +
                '}';*/
    }

}
