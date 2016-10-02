package srm680;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    if (atLeast.length == 0) {
      return new int[0];
    }

    final int[] positions = new int[atLeast.length];
    final List<Integer> sortedBears = new ArrayList<>();

    positions[0] = atLeast[0];
    sortedBears.add(atLeast[0]);

    for (int i = 1; i < atLeast.length; i++) {
      for (int k = 0; k < sortedBears.size(); k++) {
//        if (k == sortedBears.size() - 1 || )


        if (sortedBears.get(k) - d >= atLeast[i]) {
          if (k == 0) {
            final int position = atLeast[i];
            positions[i] = position;
            sortedBears.add(0, position);
            break;
          } else if (sortedBears.get(k) - d - d > sortedBears.get(k - 1)) {
            final int position = Math.max(sortedBears.get(k - 1) + d, atLeast[i]);
            positions[i] = position;
            sortedBears.add(k, position);
            break;
          }
        }
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
