import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneralUtil {

    private static final Random random = new Random();

    // 随机生成 count 个数的整型数组, 其中每个数字的最大值为 max
    public static List<Integer> randomNumbers(int count, int max) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(max));
        }
        return numbers;
    }

}
