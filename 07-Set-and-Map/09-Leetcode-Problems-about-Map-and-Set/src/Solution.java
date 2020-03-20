import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        // 将 nums1 数组中的元素存储到 set1 集合中 (已去重)
        Set<Integer> set1 = new TreeSet<>();
        for (int num1 : nums1) {
            set1.add(num1);
        }

        // 遍历 nums2 数组, 将 set1 集合中存在的元素添加到 list 中
        List<Integer> list = new ArrayList<>();
        for (int num2 : nums2) {
            if (set1.contains(num2)) {
                list.add(num2);
                // 将存在的元素 num2 添加到 list 后, 需要从 set1 中移除, 确保 list 中的数据不重复
                set1.remove(num2);
            }
        }

        // 返回 list
        Integer[] integers = list.toArray(new Integer[0]);
        int[] ints = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i];
        }
        return ints;
    }

}
