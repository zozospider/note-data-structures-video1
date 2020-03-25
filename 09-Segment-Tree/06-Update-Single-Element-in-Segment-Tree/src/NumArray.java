public class NumArray {

    // 存储 nums 数组
    private int[] data;

    // 存储 nums 数组前 n 个数的和 (n 从 0 开始)
    // sum[0] = nums[0];
    // sum[1] = nums[0] + nums[1];
    // sum[2] = nums[0] + nums[1] + nums[2];
    private int[] sum;

    // 初始化 data 和 sum
    // 时间复杂度: O(n)
    public NumArray(int[] nums) {

        if (nums != null && nums.length > 0) {

            // 初始化 data
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }

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

    // 将索引 i 位置的值, 更新为 val
    // 时间复杂度: O(n) (此方法在 LeetCode 上无法通过, 因为多次调用更新的时间复杂度太高: m * O(n))
    public void update(int i, int val) {

        // 更新 data[i] 的值
        data[i] = val;

        // 由于 data[i] 的值发生变化, 需要对 i 之后的 sum 值重新计算
        for (int x = i; x < data.length; x++) {
            if (x == 0) {
                sum[x] = data[x];
            } else {
                sum[x] = sum[x - 1] + data[x];
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
