import java.util.Random;

public class Test {

    public static void main(String[] args) {

        // 对于时间复杂度为 O(n) 的逻辑, count 测试值不能过大
        int count = 100_000;

        // 时间复杂度:
        // enqueue: O(1), dequeue: O(n)
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        long arrayTime = testQueue(arrayQueue, count);
        System.out.println("ArrayQueue time: " + arrayTime + "ms");

        // 时间复杂度:
        // enqueue: O(1), dequeue: O(1)
        Queue<Integer> loopQueue = new LoopQueue<>();
        long loopTime = testQueue(loopQueue, count);
        System.out.println("LoopQueue time: " + loopTime + "ms");

        // 时间复杂度:
        // enqueue: O(1), dequeue: O(1)
        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        long linkedListTime = testQueue(linkedListQueue, count);
        System.out.println("LinkedListQueue time: " + linkedListTime + "ms");

        // 以上 LoopQueue 和 LinkedListQueue 两个时间比较很复杂, 两者的时间复杂度都是 O(1), 具体差异包括:
        // LoopQueue: 包含数组拷贝操作
        // LinkedListQueue: 包含 new Node 对象操作
        // 所以在不同硬件, 操作系统, JVM 版本, count 值等因素下, 两者的表现都可能不一样
    }

    // 测试使用 queue 运行 count 个 enqueue 和 dequeue 操作所需要的时间, 单位: 毫秒
    private static long testQueue(Queue<Integer> queue, int count) {

        Random random = new Random();

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        // 循环 count 次入队操作
        for (int i = 0; i < count; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        // 循环 count 次出队操作
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }

        // 记录结束时间 (单位: 毫秒)
        long end = System.currentTimeMillis();
        // 记录结束时间 (单位: 纳秒)
        // long end = System.nanoTime();

        // 返回间隔时间 (单位: 毫秒)
        return end - start;
        // 返回间隔时间 (单位: 秒)
        // return (end - start) / 1_000_000_000.0;
    }

}
