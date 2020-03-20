import java.util.Arrays;

/**
 * 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 * 349. 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/description/
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Main {

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1() {
        int[] nums1 = {1, 2, 2, 3};
        int[] nums2 = {2, 2};

        Solution solution = new Solution();
        int[] intersection = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    private static void main2() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        Solution solution = new Solution();
        int[] intersection = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

}
