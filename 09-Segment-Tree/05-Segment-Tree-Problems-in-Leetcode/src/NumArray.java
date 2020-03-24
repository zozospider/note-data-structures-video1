public class NumArray {

    // 内部用线段树来存储数据
    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {

        if (nums.length > 0) {

            Integer[] integers = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                integers[i] = nums[i];
            }

            // 初始化线段树 (合并方式为两个数字的和)
            segmentTree = new SegmentTree<>(integers, (i1, i2) -> i1 + i2);
        }
    }

    public int sumRange(int i, int j) {

        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null.");
        }

        // 返回线段树区间查询结果 (两个数字的和)
        return segmentTree.query(i, j);
    }

}
