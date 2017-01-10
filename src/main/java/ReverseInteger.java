import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 1/10/17.
 */
public class ReverseInteger {
  /**
   * Reverse an integer.
   * eg: 1234 -> 4321
   * eg: 9000 -> 9
   * eg: -123 -> -321
   * @param i integer to be reversed
   * @return the reversed integer
   */
  public static int reverse(int i) {
    long reversed = reverseToLong(i);
    return (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) ? 0 : (int)reversed;
  }

  private static long reverseToLong(int i) {
    if (i < 0) {
      return Long.valueOf(new StringBuilder().append(Math.abs((long)i)).append('-').reverse().toString());
    } else {
      return Long.valueOf(new StringBuilder().append(i).reverse().toString());
    }
  }

  public static class ReverseIntegerTest {
    @Test
    public void test0() {
      assertEquals(0, reverse(1534236469));
    }

    @Test
    public void test1() {
      assertEquals(0, reverse(-2147483648));
    }
  }
}
