import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 随机生成 100 万个单词, 每个单词 2 个小写字母
        List<String> words = GeneralUtils.randomWords(2, 1_000_000);
        System.out.println("Total words: " + words.size());

        AVLTree<String, Integer> avlTree = new AVLTree<>();
        for (String word : words) {
            if (!avlTree.contains(word)) {
                avlTree.add(word, 1);
            } else {
                int number = avlTree.get(word);
                avlTree.set(word, (number + 1));
            }
        }

    }

}
