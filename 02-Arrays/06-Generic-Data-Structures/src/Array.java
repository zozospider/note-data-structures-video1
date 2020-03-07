public class Array<E> {

    // 用于存放元素的数组
    private E[] data;
    // 当前数组中的元素个数
    private int size;

    // 构造函数, 传入数组的容量 capacity 构造 Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参数构造函数, 默认数组容量 capacity = 10
    public Array() {
        this(10);
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取 index 索引位置的元素
    public E get(int index) {

        // 如果要获取的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    // 修改 index 索引位置的元素为 e
    public void set(int index, E e) {

        // 如果要插入的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素 e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素 e 所在的索引, 如果不存在元素 e, 则返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 向所有元素后添加一个新元素 (直接实现方式)
    public void addLast_v0(E e) {

        // 如果数组中的元素个数等于数组的容量, 说明数组已经满了, 抛出异常
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }

        // 在 size 索引位置 (即所有元素最后位置) 设置新元素
        data[size] = e;

        // 元素个数加 1
        size++;
    }

    // 在 index 索引位置插入一个新元素 e
    public void add(int index, E e) {

        // 如果数组中的元素个数等于数组的容量, 说明数组已经满了, 抛出异常
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }

        // 如果要插入的索引位置小于 0, 或者大于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        // 从最后一个元素开始, 一直往前, 直到 index 索引位置, 所有数据都依次往后移动一个位置, 为 index 索引位置腾出空间
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        // 此时 index 索引位置的元素为原 index 元素对应的值, 因为原值已经移动到 (index + 1) 位置处, 所以直接 index 索引位置覆盖即可.
        data[index] = e;

        // 元素个数加 1
        size++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 从数组中删除 index 索引位置的元素, 返回删除的元素
    public E remove(int index) {

        // 如果要删除的索引位置小于 0, 或者大于等于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        // 记录要删除的元素
        E e = data[index];

        // 从 index 索引位置后一位开始, 一直往后, 直到最后一个元素, 所有数据都依次往前移动一个位置
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        // 元素个数减 1
        size--;

        // 释放 size 索引位置的元素 (loitering objects) 的内存
        // loitering objects != memory leak
        data[size] = null;

        return e;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素 e
    public void removeElement(E e) {

        // 查找数组中元素 e 所在的索引
        int index = find(e);

        // 如果元素存在, 则删除
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d, ", size, data.length));
        builder.append("data = [");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != (size - 1)) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();

        /*return "Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';*/
    }

}
