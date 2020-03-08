public class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < s.length(); i++) {

            // 获取当前字符
            char c = s.charAt(i);

            // 匹配开始符号压入栈顶, 如果符合条件则继续循环下一个字符
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            // 如果为空, 则匹配失败
            if (stack.isEmpty()) {
                return false;
            }

            // 取出栈顶元素
            char top = stack.pop();

            // 匹配结束符号, 如果符合条件则继续循环下一个字符
            if (c == ')' && top == '(') {
                continue;
            }
            if (c == '}' && top == '{') {
                continue;
            }
            if (c == ']' && top == '[') {
                continue;
            }

            // 不满足以上条件, 则匹配失败
            return false;
        }

        // 如果栈中剩余字符为空, 则匹配成功
        return stack.isEmpty();
    }

}
