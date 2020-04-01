import java.util.List;

public class Test2 {

    public static void main(String[] args) {

        // 随机生成 100 万个单词, 每个单词 2 个小写字母 (不同的测试参数和 key 类型对结果会有影响)
        int count = 1_000_000;
        int charNumber = 5;

        List<String> words = GeneralUtils.randomWords(count, charNumber);
        System.out.println("Total words: " + words.size());

        // 时间复杂度:
        // TODO
        Map<String, Object> rbtMap = new RBTreeMap<>();
        long rbtTime = testMap(rbtMap, words);
        System.out.println("Total different words: " + rbtMap.getSize());
        System.out.println("RBTreeMap time: " + rbtTime + "ms");

        System.out.println("------");

        // 时间复杂度:
        // TODO
        Map<String, Object> avlMap = new AVLTreeMap<>();
        long avlTime = testMap(avlMap, words);
        System.out.println("Total different words: " + avlMap.getSize());
        System.out.println("AVLMap time: " + avlTime + "ms");

        System.out.println("------");

        // 时间复杂度:
        // TODO
        Map<String, Object> bstMap = new BSTMap<>();
        long bstTime = testMap(bstMap, words);
        System.out.println("Total different words: " + bstMap.getSize());
        System.out.println("BSTMap time: " + bstTime + "ms");
    }

    private static long testMap(Map<String, Object> map, List<String> words) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (String word : words) {
            map.add(word, null);
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
