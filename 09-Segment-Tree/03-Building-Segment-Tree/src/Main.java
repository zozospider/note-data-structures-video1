import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        System.out.println(Arrays.toString(nums));

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (e1, e2) -> e1 + e2);
        System.out.println(segmentTree);
    }

}
