/**
 * 307. Range Sum Query - Mutable
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * Note:
 * 1. The array is only modifiable by the update function.
 * 2. You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class LeetCode307 {

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1() {

        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);

        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    private static void main2() {

        int[] nums = {1, 3, 5};
        NumArray2 numArray2 = new NumArray2(nums);

        System.out.println(numArray2.sumRange(0, 2));
        numArray2.update(1, 2);
        System.out.println(numArray2.sumRange(0, 2));
    }

}
