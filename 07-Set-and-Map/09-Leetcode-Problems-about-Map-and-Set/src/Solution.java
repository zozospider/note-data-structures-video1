import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new TreeSet<>();
        for (int num1 : nums1) {
            set.add(num1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num2 : nums2) {
            if (set.contains(num2)) {
                list.add(num2);
                set.remove(num2);
            }
        }

        Integer[] integers = list.toArray(new Integer[0]);
        int[] ints = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i];
        }
        return ints;
    }

}
