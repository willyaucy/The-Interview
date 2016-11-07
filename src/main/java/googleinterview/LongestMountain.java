package googleinterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given an array of of int, find the longest mountain.
 * The definition of mountain is that all integer in the middles are strictly greater than
 * the left most and right most.
 *
 * Ex: 1 5 6 7 9 5 10 5 2 0 5 9
 * 1 to 0 is the longest mountain
 * 0 5 9 is another mountain but not the longest
 *
 * Follow up: If this is a 2d space...  How do you do it
 */
public class LongestMountain {
  public static int findLongestMountain(int[] array) {
    return 0;
  }

  public static class LongestMountainTest {
    @Test
    public static void test1() {
      assertEquals(10, findLongestMountain(new int[] {1, 5, 6, 7, 9, 5, 10, 5, 2, 0, 5, 9}));
    }
  }
}
