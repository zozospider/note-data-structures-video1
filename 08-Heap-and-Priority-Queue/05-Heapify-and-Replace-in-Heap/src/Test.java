import java.util.List;

public class Test {

    public static void main(String[] args) {

        // 随机生成 100 万个整数, 每个整数最大值 1000
        List<Integer> numbers = GeneralUtils.randomNumbers(1_000_000, 1_000);
        Integer[] numberArray = numbers.toArray(new Integer[0]);
        System.out.println("Total numbers: " + numberArray.length);

        // 时间复杂度:
        // MaxHeap: O(n)
        long heapifyTime = testHeap(true, numberArray);
        System.out.println("With heapify time: " + heapifyTime + "ms");

        // 时间复杂度:
        // multi add: O(n log n)
        long noHeapifyTime = testHeap(false, numberArray);
        System.out.println("Without heapify time: " + noHeapifyTime + "ms");

        // O(n) 和 O(n log n) 两者的实际性能测试结果可能相差不大, JVM 可能会对非 Heapify 操作进行优化

        System.out.println("------");

        MaxHeap<Integer> maxHeap = new MaxHeap<>(numberArray);
        List<Integer> replaceNumbers = GeneralUtils.randomNumbers(100_000, 10_000);

        // 时间复杂度:
        // replace: O(log n)
        long replaceTime = testHeapReplace(maxHeap, true, replaceNumbers);
        System.out.println("replace time: " + replaceTime + "ms");

        // 时间复杂度:
        // replace2: 2 * O(log n)
        long replace2Time = testHeapReplace(maxHeap, false, replaceNumbers);
        System.out.println("replace2 time: " + replace2Time + "ms");
    }

    private static long testHeap(boolean isHeapify, Integer[] numbers) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        if (isHeapify) {
            MaxHeap<Integer> maxHeap = new MaxHeap<>(numbers);
        } else {
            MaxHeap<Integer> maxHeap = new MaxHeap<>();
            for (Integer number : numbers) {
                maxHeap.add(number);
            }
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

    private static long testHeapReplace(MaxHeap<Integer> maxHeap, boolean isReplace, List<Integer> replaceNumbers) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        if (isReplace) {
            for (Integer replaceNumber : replaceNumbers) {
                maxHeap.replace(replaceNumber);
            }
            System.out.println("1: " + maxHeap.getSize());
        } else {
            for (Integer replaceNumber : replaceNumbers) {
                maxHeap.replace2(replaceNumber);
            }
            System.out.println("2: " + maxHeap.getSize());
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
