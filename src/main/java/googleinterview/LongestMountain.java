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
 * Loop through indices, keep track of leftmost, if current element is less than or equal to left, stop
 * we have found the longest mountain that ends at i, what is the longest mountain that ends at i+1, not a concern.
 * so we find the longest mountain that starts at i+1 instead.
 * we repeat until we reach the end of the list
 *
 * Follow up: If this is a 2d space...  How do you do it
 */
public class LongestMountain {
  public static int findLongestMountain(int[] array) {
    if (array.length <= 2) {
      return array.length;
    }

    int longestMountainFound = 2, mountainStart = 0;

    while (mountainStart < array.length - 1) {
      for (int currentIndex = mountainStart + 1; currentIndex < array.length; currentIndex++) {
        if (currentIndex == array.length - 1 || array[currentIndex] <= array[mountainStart]) {
          longestMountainFound = Math.max(longestMountainFound, currentIndex - mountainStart + 1);
          mountainStart = currentIndex;
          break;
        }
      }
    }

    return longestMountainFound;
  }

  public static class LongestMountainTest {
    @Test
    public void test1() {
      assertEquals(10, findLongestMountain(new int[] {1, 5, 6, 7, 9, 5, 10, 5, 2, 0, 5, 9}));
    }
  }
}
