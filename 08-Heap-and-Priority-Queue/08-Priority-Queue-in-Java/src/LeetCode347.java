import java.util.List;

/**
 * 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class LeetCode347 {

    public static void main(String[] args) {
        main1();
        main2();
        main3();
    }

    private static void main1() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> keys = new Solution().topKFrequent(nums, k);
        List<Integer> keys2 = new Solution2().topKFrequent(nums, k);
        List<Integer> keys3 = new Solution3().topKFrequent(nums, k);
        List<Integer> keys4 = new Solution4().topKFrequent(nums, k);
        List<Integer> keys5 = new Solution5().topKFrequent(nums, k);
        System.out.println(keys + " == " + keys2 + " == " + keys3 + " == " + keys4 + " == " + keys5);
    }

    private static void main2() {
        int[] nums = {1};
        int k = 1;
        List<Integer> keys = new Solution().topKFrequent(nums, k);
        List<Integer> keys2 = new Solution2().topKFrequent(nums, k);
        List<Integer> keys3 = new Solution3().topKFrequent(nums, k);
        List<Integer> keys4 = new Solution4().topKFrequent(nums, k);
        List<Integer> keys5 = new Solution5().topKFrequent(nums, k);
        System.out.println(keys + " == " + keys2 + " == " + keys3 + " == " + keys4 + " == " + keys5);
    }

    private static void main3() {
        int[] nums = {2, 1, 2, 1, 3, 1, 1, 4, 5, 5};
        int k = 3;
        List<Integer> keys = new Solution().topKFrequent(nums, k);
        List<Integer> keys2 = new Solution2().topKFrequent(nums, k);
        List<Integer> keys3 = new Solution3().topKFrequent(nums, k);
        List<Integer> keys4 = new Solution4().topKFrequent(nums, k);
        List<Integer> keys5 = new Solution5().topKFrequent(nums, k);
        System.out.println(keys + " == " + keys2 + " == " + keys3 + " == " + keys4 + " == " + keys5);
    }

}
