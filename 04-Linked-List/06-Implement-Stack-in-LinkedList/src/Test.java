import java.util.Random;

public class Test {

    public static void main(String[] args) {

        int count = 100_000;

        // 均摊复杂度:
        // push, pop: O(1)
        Stack<Integer> arrayStack = new ArrayStack<>();
        long arrayTime = testStack(arrayStack, count);
        System.out.println("ArrayStack time: " + arrayTime + "ms");

        // 时间复杂度:
        // push, pop: O(1)
        Stack<Integer> linkedListStack = new LinkedListStack<>();
        long linkedListTime = testStack(linkedListStack, count);
        System.out.println("LinkedListStack time: " + linkedListTime + "ms");

        // 以上两个时间比较很复杂, 两者的时间复杂度都是 O(1), 具体差异包括:
        // ArrayStack: 包含数组拷贝操作
        // LinkedListStack: 包含 new Node 对象操作
        // 所以在不同硬件, 操作系统, JVM 版本, count 值等因素下, 两者的表现都可能不一样
    }

    // 测试使用 stack 运行 count 个 push 和 pop 操作所需要的时间, 单位: 毫秒
    private static long testStack(Stack<Integer> stack, int count) {

        Random random = new Random();

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        // 循环 count 次入栈操作
        for (int i = 0; i < count; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        // 循环 count 次出栈操作
        for (int i = 0; i < count; i++) {
            stack.pop();
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
