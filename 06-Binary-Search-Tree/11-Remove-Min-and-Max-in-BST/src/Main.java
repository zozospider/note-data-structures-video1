import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            bst.add(random.nextInt(1000));
        }

        List<Integer> list = new ArrayList<>();
        while (!bst.isEmpty()) {
            list.add(bst.removeMin());
        }

        System.out.println(list);
    }

}
