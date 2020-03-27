import java.util.Map;
import java.util.TreeMap;

public class Trie {

    private class Node {

        // 当前节点是否为某个单词的最后节点
        boolean isWord;

        // 当前节点指向的下游节点的映射
        // key 为可标识下游某个节点的字母
        // value 为下游节点对象本身 (包含 isWord 和 next)
        Map<Character, Node> next;

        Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
    }

    // 根节点, 该节点本身不代表任何字母
    private Node root;

    // 当前 Trie 中的单词个数
    private int size;

    public Trie() {
        root = new Node(false);
        size = 0;
    }

    // 获取 Trie 中的单词个数
    public int getSize() {
        return size;
    }

    // 向 Trie 中添加一个单词 word
    // 时间复杂度: O(m) (m 为单词字母个数) (此处忽略 Trie 内部使用的 TreeMap 的时间复杂度)
    public void add(String word) {

        // 用于记录某个节点, 在循环中实际操作的是 current.next (current 的下游某个节点)
        Node current = root;

        // 从 root 开始, 依次遍历 word 中的每个字母, 并判断字母对应的 Trie 中的节点是否存在, 如果存在则不做任何处理, 如果不存在则创建节点
        for (int i = 0; i < word.length(); i++) {

            // 当前字母
            char c = word.charAt(i);

            // 判断 Trie 中的下游节点是否存在该字母 key
            // 如果存在则说明当前字母已经在 Trie 中, 不需要做任何处理
            // 如果不存在则创建下游节点 (由于是新创建的节点, 所以它的 next 为 null, isWord 在下面的逻辑赋值)
            if (current.next.get(c) == null) {
                current.next.put(c, new Node(false));
            }

            // current 赋值为 next 下游中 key 为当前字母的节点
            current = current.next.get(c);
        }

        // 如果遍历到的最后一个字母对应的节点已经标记为 isWord, 说明该单词已存在于 Trie 中, 不做任何处理, 否则设置为 isWord, 并将单词个数加 1
        if (!current.isWord) {
            current.isWord = true;
            size++;
        }
    }

    // 查询单词 word 是否在 Trie 中
    // 时间复杂度: O(m) (m 为单词字母个数) (此处忽略 Trie 内部使用的 TreeMap 的时间复杂度)
    public boolean contains(String word) {

        // 用于记录某个节点, 在循环中实际操作的是 current.next (current 的下游某个节点)
        Node current = root;

        // 从 root 开始, 依次遍历 word 中的每个字母, 并判断字母对应的 Trie 中的节点是否存在, 如果不存在则返回 false
        for (int i = 0; i < word.length(); i++) {

            // 当前字母
            char c = word.charAt(i);

            // 判断 Trie 中的下游节点是否存在该字母 key
            // 如果存在则说明当前字母已经在 Trie 中, 继续遍历下一个字母进行判断
            // 如果不存在则返回 false
            if (current.next.get(c) == null) {
                return false;
            }

            // current 赋值为 next 下游中 key 为当前字母的节点
            current = current.next.get(c);
        }

        // 如果遍历到最后一个字母都没有返回 false, 说明所有字母都存在于 Trie 中, 此时只需判断最后一个字母是否为 isWord
        return current.isWord;
    }

    // 查询在 Trie 中是否有单词以 prefix 为前缀
    // 时间复杂度: O(m) (m 为单词字母个数) (此处忽略 Trie 内部使用的 TreeMap 的时间复杂度)
    public boolean isPrefix(String prefix) {

        // 用于记录某个节点, 在循环中实际操作的是 current.next (current 的下游某个节点)
        Node current = root;

        // 从 root 开始, 依次遍历 word 中的每个字母, 并判断字母对应的 Trie 中的节点是否存在, 如果不存在则返回 false
        for (int i = 0; i < prefix.length(); i++) {

            // 当前字母
            char c = prefix.charAt(i);

            // 判断 Trie 中的下游节点是否存在该字母 key
            // 如果存在则说明当前字母已经在 Trie 中, 继续遍历下一个字母进行判断
            // 如果不存在则返回 false
            if (current.next.get(c) == null) {
                return false;
            }

            // current 赋值为 next 下游中 key 为当前字母的节点
            current = current.next.get(c);
        }

        // 如果遍历到最后一个字母都没有返回 false, 说明所有字母都存在于 Trie 中, 返回 true
        return true;
    }

}
