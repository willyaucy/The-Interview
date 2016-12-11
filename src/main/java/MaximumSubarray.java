import org.junit.Test;

import java.lang.IllegalArgumentException;

import static org.junit.Assert.assertEquals;

public class MaximumSubarray {
  public static int maxSubArray(int[] nums) {
    if (nums.length == 0) {
      throw new IllegalArgumentException();
    }

    int maxSum = nums[0];
    int maxSumAtLastIndex = nums[0];

    for (int i = 1; i < nums.length; i++) {
      maxSumAtLastIndex = nums[i] + Math.max(0, maxSumAtLastIndex);
      maxSum = Math.max(maxSum, maxSumAtLastIndex);
    }

    return maxSum;
  }

  public static class MaximumSubarrayTest {
    @Test
    public void test0() {
      assertEquals(6, maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }
  }
}