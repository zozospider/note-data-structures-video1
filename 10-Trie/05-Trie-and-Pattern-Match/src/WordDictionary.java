import java.util.Map;
import java.util.TreeMap;

public class WordDictionary {

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

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node(false);
    }

    /**
     * Adds a word into the data structure.
     */
    // 向 Trie 中添加一个单词 word
    public void addWord(String word) {

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
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    // 返回以 Node 节点为根的 Trie 中是否存在 word 中第 index 个字符及其之后的内容
    private boolean match(Node node, String word, int index) {

        // 递归终止
        // 如果 index 是 word 的最后一个字符, 说明 index 前面的字符都匹配成功了, 返回当前 Node 是否为某个单词 (该单词就是 word) 的最后一个字母
        if (index == word.length()) {
            return node.isWord;
        }

        // 取出第 index 个字符
        char c = word.charAt(index);

        // 需要分开处理第 index 个字符是否为通用匹配符的情况

        if (c != '.') {
            // 如果第 index 个字符不是通用匹配符

            // 判断当前 Node 节点的下游节点中是否存在以 c 为 key 的 Node

            if (node.next.get(c) == null) {
                // 递归终止
                // 如果不存在, 则第 index 个字符匹配失败 (整个 word 也匹配失败), 终止递归
                return false;

            } else {
                // 递归调用
                // 如果存在, 则第 index 个字符匹配成功, 继续调用以 Node 的下游节点 (node.next.get(c)) 为根的的 Trie 中是否存在 word 中第 (index + 1) 个字符及其之后的内容
                return match(node.next.get(c), word, (index + 1));

            }
        } else {
            // 如果第 index 个字符是通用匹配符

            // 则说明当前 Node 节点的所有下游节点都能成功匹配当前字符, 需要继续判断下一个 (index + 1) 字符是否匹配

            // 因为当前 Node 节点的所有下游节点都满足继续匹配的条件, 所以只要有一个下游节点能成功匹配 word 中第 (index + 1) 个字符及其之后的内容即可

            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, (index + 1))) {
                    return true;
                }
            }

            // 所有下游节点都不能匹配 word 中第 (index + 1) 个字符及其之后的内容, 则匹配失败
            return false;
        }
    }

}
