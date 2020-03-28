import java.util.Random;

public class Test {

    public static void main(String[] args) {
        // main1();
        main2();
    }

    private static void main1() {

        // UnionFind2 / UnionFind3 快于 UnionFind1 (UnionFind1 数组长度大 (n = 100_000), 直接操作数组慢)
        //int size = 100_000;
        //int count = 1_000;

        // UnionFind1 快于 UnionFind2 / UnionFind3 (UnionFind1 数组长度小 (n = 1_000), 直接操作数组快)
        // int size = 1_000;
        // int count = 100_000;

        // UnionFind3 快于 UnionFind1 快于 UnionFind2 (UnionFind3 树高度低, UnionFind1 数组长度中 (n = 60_000), UnionFind2 树高度高)
        int size = 60_000;
        int count = 60_000;

        // 时间复杂度:
        // unionElements: O(n)
        // isConnected: O(1)
        UF uf1 = new UnionFind1(size);
        long uf1Time = testUF(uf1, count);
        System.out.println("UnionFind1 time: " + uf1Time + "ms");

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf2 = new UnionFind2(size);
        long uf2Time = testUF(uf2, count);
        System.out.println("UnionFind2 time: " + uf2Time + "ms");

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf3 = new UnionFind3(size);
        long uf3Time = testUF(uf3, count);
        System.out.println("UnionFind3 time: " + uf3Time + "ms");
    }

    private static void main2() {

        // UnionFind5 快于 UnionFind6 快于 UnionFind4 快于 UnionFind3 (UnionFind6 递归需要消耗一定资源, 所以通常比 UnionFind5 慢一点)
        int size = 2_000_000;
        int count = 2_000_000;

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf3 = new UnionFind3(size);
        long uf3Time = testUF(uf3, count);
        System.out.println("UnionFind3 time: " + uf3Time + "ms");

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf4 = new UnionFind4(size);
        long uf4Time = testUF(uf4, count);
        System.out.println("UnionFind4 time: " + uf4Time + "ms");

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf5 = new UnionFind5(size);
        long uf5Time = testUF(uf5, count);
        System.out.println("UnionFind5 time: " + uf5Time + "ms");

        // 时间复杂度:
        // unionElements: O(h)
        // isConnected: O(h)
        UF uf6 = new UnionFind6(size);
        long uf6Time = testUF(uf6, count);
        System.out.println("UnionFind6 time: " + uf6Time + "ms");
    }

    private static long testUF(UF uf, int count) {

        Random random = new Random();

        int size = uf.getSize();

        // 记录开始时间 (单位: 毫秒)
        long start = System.currentTimeMillis();
        // 记录开始时间 (单位: 纳秒)
        // long start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.unionElements(p, q);
        }

        for (int i = 0; i < count; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p, q);
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
