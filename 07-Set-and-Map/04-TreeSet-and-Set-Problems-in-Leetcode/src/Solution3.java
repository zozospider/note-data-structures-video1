public class Solution3 {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        // 使用集合存储非重复的摩尔斯密码
        Set<String> set = new LinkedListSet<>();

        // 遍历所有单词, 求出每个单词的摩尔斯密码
        for (String word : words) {

            StringBuilder res = new StringBuilder();

            // 当前单词中每个字母对应的摩斯密码
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }

            // 如果当前单词对应的摩斯码已存在, 不会重复添加, 确保了存储在集合中的摩尔斯密码唯一性
            set.add(res.toString());
        }

        // 返回集合中不同摩尔斯密码的数量
        return set.getSize();
    }

}
