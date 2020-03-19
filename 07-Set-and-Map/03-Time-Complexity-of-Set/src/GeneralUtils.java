import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneralUtils {

    private static final Random random = new Random();

    // 随机生成 count 个数的整型数组, 其中每个数字的最大值为 max
    public static List<Integer> randomNumbers(int count, int max) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(max));
        }
        return numbers;
    }

    public static List<String> generalWords(int charNumber, int count) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < charNumber; j++) {
                builder.append((char) (random.nextInt(26) + 'a'));
            }
            words.add(builder.toString());
        }
        return words;
    }

}
