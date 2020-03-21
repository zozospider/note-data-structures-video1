public class MaxHeapPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public MaxHeapPriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    // 时间复杂度: O(1)
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    // 时间复杂度: O(log n)
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    // 时间复杂度: O(log n)
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

}
