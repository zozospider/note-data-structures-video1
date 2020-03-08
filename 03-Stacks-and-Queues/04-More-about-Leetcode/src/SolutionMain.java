/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class SolutionMain {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isValid("{[()]}"));
        System.out.println(solution.isValid("{()[]}"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("{[[(())]]}"));

        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{([)]}"));
        System.out.println(solution.isValid("("));

        System.out.println(solution.isValid(""));
    }

}
