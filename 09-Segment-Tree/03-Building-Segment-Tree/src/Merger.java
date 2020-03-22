public interface Merger<E> {

    // 返回合并左右孩子后的值
    E merge(E e1, E e2);

}
