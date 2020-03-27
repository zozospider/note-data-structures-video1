import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 随机生成 40 万个单词, 每个单词 1 至 5 个小写字母
        List<String> words = new ArrayList<>();
        List<String> words1 = GeneralUtils.randomWords(1, 100_000);
        List<String> words2 = GeneralUtils.randomWords(2, 100_000);
        List<String> words3 = GeneralUtils.randomWords(3, 100_000);
        List<String> words4 = GeneralUtils.randomWords(5, 100_000);
        words.addAll(words1);
        words.addAll(words2);
        words.addAll(words3);
        words.addAll(words4);
        System.out.println("Total words: " + words.size());

        // 时间复杂度:
        // 平均复杂度:
        // add: O(h) = O(log n)
        // contains: O(h) = O(log n)
        Set<String> bstSet = new BSTSet<>();
        long bstTime = testSet(bstSet, words);
        System.out.println("Total different words: " + bstSet.getSize());
        System.out.println("BSTSet words: " + bstTime + "ms");

        // 时间复杂度 (此处忽略 Trie 内部使用的 TreeMap 的时间复杂度):
        // add: O(m)
        // contains: O(m)
        Set<String> trieSet = new TrieSet();
        long trieTime = testSet(trieSet, words);
        System.out.println("Total different words: " + trieSet.getSize());
        System.out.println("TrieSet words: " + trieTime + "ms");
    }

    private static long testSet(Set<String> set, List<String> words) {

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (String word : words) {
            set.add(word);
        }

        for (String word : words) {
            set.contains(word);
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
