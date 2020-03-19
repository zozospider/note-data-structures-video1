import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 随机生成 100 万个整数, 每个整数最大值 1000
        List<Integer> numbers = GeneralUtils.randomNumbers(1_000_000, 1000);
        System.out.println("Total numbers: " + numbers.size());

        // 平均复杂度:
        // O(h) = O(log n)
        Set<Integer> bstSet = new BSTSet<>();
        long bstTime = testSet(bstSet, numbers);
        System.out.println("Total different numbers: " + bstSet.getSize());
        System.out.println("BSTSet time: " + bstTime + "ms");

        // 时间复杂度:
        // add: O(n)
        Set<Integer> linkedListSet = new LinkedListSet<>();
        long linkedListTime = testSet(linkedListSet, numbers);
        System.out.println("Total different numbers: " + linkedListSet.getSize());
        System.out.println("LinkedListSet time: " + linkedListTime + "ms");
    }

    private static long testSet(Set<Integer> set, List<Integer> numbers) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (Integer number : numbers) {
            set.add(number);
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
