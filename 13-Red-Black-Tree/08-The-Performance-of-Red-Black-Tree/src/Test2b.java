import java.util.List;

public class Test2b {

    public static void main(String[] args) {

        // 随机生成 100 万个数字, 每个数字最大值为 1000 万 (不同的测试参数和 key 类型对结果会有影响)
        int count = 1_000_000;
        int max = 10_000_000;

        List<Integer> numbers = GeneralUtils.randomNumbers(count, max);
        System.out.println("Total numbers: " + numbers.size());

        // 时间复杂度:
        // TODO
        Map<Integer, Object> rbtMap = new RBTreeMap<>();
        long rbtTime = testMap(rbtMap, numbers);
        System.out.println("Total different words: " + rbtMap.getSize());
        System.out.println("RBTreeMap time: " + rbtTime + "ms");

        System.out.println("------");

        // 时间复杂度:
        // TODO
        Map<Integer, Object> avlMap = new AVLTreeMap<>();
        long avlTime = testMap(avlMap, numbers);
        System.out.println("Total different words: " + avlMap.getSize());
        System.out.println("AVLMap time: " + avlTime + "ms");

        System.out.println("------");

        // 时间复杂度:
        // TODO
        Map<Integer, Object> bstMap = new BSTMap<>();
        long bstTime = testMap(bstMap, numbers);
        System.out.println("Total different words: " + bstMap.getSize());
        System.out.println("BSTMap time: " + bstTime + "ms");
    }

    private static long testMap(Map<Integer, Object> map, List<Integer> numbers) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (Integer number : numbers) {
            map.add(number, null);
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
