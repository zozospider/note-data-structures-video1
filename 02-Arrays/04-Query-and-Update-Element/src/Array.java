import java.util.Arrays;

public class Array {

    // 用于存放 int 元素的数组
    private int[] data;
    // 当前数组中的元素个数
    private int size;

    // 构造函数, 传入数组的容量 capacity 构造 Array
    public Array(int capacity) {
        data = new int[capacity];
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

    // 向所有元素后添加一个新元素 (直接实现方式)
    public void addLast_v0(int e) {

        // 如果数组中的元素个数等于数组的容量, 说明数组已经满了, 抛出异常
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }

        // 在 size 索引位置 (即所有元素最后位置) 设置新元素
        data[size] = e;

        // 元素个数加 1
        size++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(int e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(int e) {
        add(0, e);
    }

    // 在 index 索引位置插入一个新元素 e
    public void add(int index, int e) {

        // 如果数组中的元素个数等于数组的容量, 说明数组已经满了, 抛出异常
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }

        // 如果要插入的索引位置小于 0, 或者大于当前 size 索引位置 (即所有元素最后位置), 则认为是非法操作, 抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        // 从所有元素最后位置开始, 一直往前, 直到 index 索引位置, 所有数据都依次往后移动一个位置, 为 index 索引位置腾出空间
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        // 此时 index 索引位置的元素为原 index 元素对应的值, 因为原值已经移动到 (index + 1) 位置处, 所以直接 index 索引位置覆盖即可.
        data[index] = e;

        // 元素个数加 1
        size++;
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
