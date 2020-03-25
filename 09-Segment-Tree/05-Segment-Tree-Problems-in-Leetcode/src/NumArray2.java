public class NumArray2 {

    // 存储数组前 n 个数的和 (n 从 0 开始)
    // sum[0] = nums[0];
    // sum[1] = nums[0] + nums[1];
    // sum[2] = nums[0] + nums[1] + nums[2];
    private int[] sum;

    // 初始化 sum
    // 时间复杂度: O(n)
    public NumArray2(int[] nums) {

        if (nums != null && nums.length > 0) {

            // 初始化 sum (需要单独处理索引为 0 的情况)
            sum = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    sum[i] = nums[i];
                } else {
                    sum[i] = sum[i - 1] + nums[i];
                }
            }
        }
    }

    // 求区间和
    // 时间复杂度: O(1)
    public int sumRange(int i, int j) {

        if (sum == null) {
            throw new IllegalArgumentException("sum is null.");
        }

        // 需要单独处理索引为 0 的情况
        if (i == 0) {
            return sum[j];
        } else {
            return sum[j] - sum[i - 1];
        }
    }

}
