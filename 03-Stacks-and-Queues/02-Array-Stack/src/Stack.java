public interface Stack<E> {

    // 栈元素的大小
    int getSize();

    // 栈是否为空
    boolean isEmpty();

    // 将元素压入栈顶
    void push(E e);

    // 获取并删除栈顶元素
    E pop();

    // 查看栈顶元素
    E peek();

}
