package srm680;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/24/16.
 * Started: 08/24/2016 1:38am
 * Finished: 2:29am X test4 failed
 */
public class BearChairs {
  public static int[] findPositions(int[] atLeast, int d) {
    final int[] positions = new int[atLeast.length];
    final PriorityQueue<Integer> sortedBears = new PriorityQueue<>();
    int leftmostAvailable = 1;

    for (int i = 0; i < atLeast.length; i++) {
      positions[i] = Math.max(leftmostAvailable, atLeast[i]);
      sortedBears.add(positions[i]);

      while (!sortedBears.isEmpty() && sortedBears.peek() - leftmostAvailable < d) {
        leftmostAvailable = sortedBears.remove() + d;
      }
    }

    return positions;
  }

  public static class BearChairsTest {
    @Test
    public void test1() {
      assertArrayEquals(
          new int[] {1, 21, 11, 31},
          findPositions(new int[] {1,21,11,7}, 10)
      );
    }

    @Test
    public void test2() {
      assertArrayEquals(
          new int[] {1, 21, 32, 43},
          findPositions(new int[] {1,21,11,7}, 11)
      );
    }

    @Test
    public void test3() {
      assertArrayEquals(
          new int[] {1000000, 2000000, 3000000, 4000000},
          findPositions(new int[] {1000000,1000000,1000000,1}, 1000000)
      );
    }

    @Test
    public void test4() {
      assertArrayEquals(
          new int[] {1000000, 1999999, 2999998, 1},
          findPositions(new int[] {1000000,1000000,1000000,1}, 999999)
      );
    }
  }
}
