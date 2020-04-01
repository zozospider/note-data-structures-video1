import java.util.ArrayList;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {

        // 顺序生成 100 万个数字
        int n = 1_000_000;

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }

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

        // BSTMap 会退化成链表, 执行太慢, 不做对比
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
