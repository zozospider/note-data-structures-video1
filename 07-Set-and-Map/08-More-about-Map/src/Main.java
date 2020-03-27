import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 随机生成 100 万个单词, 每个单词 2 个小写字母
        List<String> words = GeneralUtils.randomWords(2, 1_000_000);
        System.out.println("Total words: " + words.size());

        // 时间复杂度:
        // TODO
        Map<String, Integer> bstMap = new BSTMap<>();
        long bstTime = testMap(bstMap, words);
        System.out.println("Total different words: " + bstMap.getSize());
        System.out.println("BSTMap time: " + bstTime + "ms");

        // 时间复杂度:
        // TODO
        Map<String, Integer> linkedListMap = new LinkedListMap<>();
        long linkedListTime = testMap(linkedListMap, words);
        System.out.println("Total different words: " + linkedListMap.getSize());
        System.out.println("LinkedListMap time: " + linkedListTime + "ms");
    }

    private static long testMap(Map<String, Integer> map, List<String> words) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (String word : words) {
            if (!map.contains(word)) {
                map.add(word, 1);
            } else {
                int number = map.get(word);
                map.set(word, number + 1);
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

}
