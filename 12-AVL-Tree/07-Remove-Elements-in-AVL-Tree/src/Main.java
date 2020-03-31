import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 随机生成 10 万个单词, 每个单词 2 个小写字母
        int count = 100_000;
        int charNumber = 2;

        List<String> words = GeneralUtils.randomWords(count, charNumber);
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

        System.out.println("is BST: " + avlTree.isBST());
        System.out.println("is BalancedTree: " + avlTree.isBalancedTree());

        for (String word : words) {
            avlTree.remove(word);
        }

        System.out.println("is empty: " + avlTree.isEmpty());
    }

}
