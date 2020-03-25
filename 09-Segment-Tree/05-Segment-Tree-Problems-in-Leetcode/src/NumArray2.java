public class NumArray2 {

    private int[] sum;

    public NumArray2(int[] nums) {
        if (nums != null && nums.length > 0) {
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

    public int sumRange(int i, int j) {
        if (sum == null) {
            throw new IllegalArgumentException("sum is null.");
        }
        if (i == 0) {
            return sum[j];
        } else {
            return sum[j] - sum[i - 1];
        }
    }

}
