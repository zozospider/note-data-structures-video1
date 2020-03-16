import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        // 删除最小值测试

        BST<Integer> minBst = new BST<>();

        for (int i = 0; i < 20; i++) {
            minBst.add(random.nextInt(1000));
        }

        List<Integer> minList = new ArrayList<>();
        while (!minBst.isEmpty()) {
            minList.add(minBst.removeMin());
        }

        System.out.println(minList);

        System.out.println("------");

        // 删除最大值测试

        BST<Integer> maxBst = new BST<>();

        for (int i = 0; i < 20; i++) {
            maxBst.add(random.nextInt(1000));
        }

        List<Integer> maxList = new ArrayList<>();
        while (!maxBst.isEmpty()) {
            maxList.add(maxBst.removeMax());
        }

        System.out.println(maxList);
    }

}
