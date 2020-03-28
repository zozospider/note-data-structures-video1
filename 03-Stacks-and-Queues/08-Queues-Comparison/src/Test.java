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
