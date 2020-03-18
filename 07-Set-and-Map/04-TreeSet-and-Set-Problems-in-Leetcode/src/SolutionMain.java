/**
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 * 804. 唯一摩尔斯密码词
 * https://leetcode.com/problems/unique-morse-code-words/description/
 * 804. Unique Morse Code Words
 *
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
 * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
 * 返回我们可以获得所有词不同单词翻译的数量。
 * 例如:
 * 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * 共有 2 种不同翻译, "--...-." 和 "--...--.".
 */
public class SolutionMain {

    public static void main(String[] args) {

        String[] words = {"gin", "zen", "gig", "msg"};

        int uniqueCount = new Solution().uniqueMorseRepresentations(words);
        int uniqueCount2 = new Solution2().uniqueMorseRepresentations(words);
        int uniqueCount3 = new Solution3().uniqueMorseRepresentations(words);

        System.out.println(uniqueCount);
        System.out.println(uniqueCount2);
        System.out.println(uniqueCount3);
    }

}
