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

}
