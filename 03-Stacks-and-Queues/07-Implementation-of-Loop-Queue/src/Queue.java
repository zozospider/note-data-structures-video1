public interface Queue<E> {

    // 队列元素的大小
    int getSize();

    // 队列是否为空
    boolean isEmpty();

    // 查看队列头部元素
    E getFront();

    // 将元素加入队列尾部
    void enqueue(E e);

    // 取出队列头部元素
    E dequeue();

}
