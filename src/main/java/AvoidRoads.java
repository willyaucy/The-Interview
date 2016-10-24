import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 9/13/16.
 * Started: 9/13/16 11:07pm
 * Finished: 9/13/16 11:56pm
 */
public class AvoidRoads {
  public static long numWays(final int width, final int height, final String[] bad) {
    long[][] numWaysCounter = new long[width + 1][height + 1];

    for (int w = 0; w <= width; w++) {
      for (int h = 0; h <= height; h++) {
        if (w == 0 && h == 0) {
          numWaysCounter[w][h] = 1;
        } else {
          if (!isBad(bad, w, h, w, h - 1)) {
            numWaysCounter[w][h] += numWaysCounter[w][h - 1];
          }

          if (!isBad(bad, w, h, w - 1, h)) {
            numWaysCounter[w][h] += numWaysCounter[w - 1][h];
          }
        }
      }
    }

    return numWaysCounter[width][height];
  }

  public static boolean isBad(String[] bad, int w1, int h1, int w2, int h2) {
    if (w1 < 0 || h1 < 0 || w2 < 0 || h2 < 0) {
      return true;
    }

    return Arrays
        .stream(bad)
        .filter(b ->
            b.equals(String.format("%d %d %d %d", w1, h1, w2, h2)) ||
            b.equals(String.format("%d %d %d %d", w2, h2, w1, h1)))
        .findAny()
        .isPresent();
  }

  public static class AvoidRoadsTest {
    @Test
    public void test1() {
      assertEquals(1166309411843295530L, numWays(19, 100, new String[]  {"1 3 0 3", "0 4 0 3", "6 1 7 1", "4 7 4 8", "2 6 3 6", "0 6 0 7", "9 3 10 3", "2 4 2 3", "2 3 2 4", "7 6 7 7", "6 3 6 4", "8 7 8 8", "1 4 1 5", "0 2 1 2", "4 3 5 3", "2 9 2 10", "1 1 1 2", "9 5 8 5", "2 7 2 8", "6 0 7 0", "6 8 7 8", "2 2 2 1", "2 9 1 9", "5 8 5 9", "1 6 2 6", "9 4 8 4", "4 1 3 1", "7 5 8 5", "5 0 5 1", "3 6 2 6", "7 9 8 9", "6 8 7 8", "4 2 4 3", "5 0 4 0", "7 2 7 3", "4 4 5 4", "8 9 7 9", "5 3 6 3", "3 7 3 6", "0 8 1 8", "7 5 7 6", "9 3 10 3", "9 6 10 6", "1 9 1 10", "7 3 8 3", "4 6 4 7", "3 6 4 6", "3 1 3 0", "6 1 6 0", "8 3 7 3"}));
    }
  }
}
