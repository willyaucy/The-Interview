import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/14/16.
 * Started: 8/14 3:54pm
 * Finished: 8/14 5:12pm
 * How the fuck do people answer these so fast?!
 */
public class BiconnectedDiv2 {
  public int minimize(int[] w1, int[] w2) {
    int cost = 0;

    for (int w : w2) {
      cost += w;
    }

    for (int i = 0; i < w1.length; i++) {
      if (i == 0 || i == w1.length - 1 || w1[i] < 0) {
        cost += w1[i];
      }
    }

    return cost;
  }

  @Test
  public void test1() {
    assertEquals(
        6,
        new BiconnectedDiv2().minimize(
            new int[] {1,2},
            new int[] {3}));
  }

  @Test
  public void test2() {
    assertEquals(
        -15,
        new BiconnectedDiv2().minimize(
            new int[] {-1,-2,-3},
            new int[] {-4, -5}));
  }

  @Test
  public void test3() {
    assertEquals(
        3,
        new BiconnectedDiv2().minimize(
            new int[] {1,100,-3,2},
            new int[] {-2,1,4}));
  }

  @Test
  public void test4() {
    assertEquals(
        -4154,
        new BiconnectedDiv2().minimize(
            new int[] {7488, -3297, -3676, -8558},
            new int[] {-3398, -1948, 9235}));
  }

  @Test
  public void test5() {
    assertEquals(
        -172752,
        new BiconnectedDiv2().minimize(
            new int[] {-7274, 3998, -9745, 6922, 7787, -668, -1846, -9843, -6976, 9513, -9483, 7306, 3364, 5903, 5496, -6485, -3938, 2523, 2290, 7148, -7791, -8452, -6507, -3540, -220, 1599, -7208, 8163, 7875, 8784, 328, 2362, 1445, 8662, 5899, -6475, 3680, 2513, -3225, -9851, -8412, -8418, -5186, -9074, 2627, 7611, 2376, -2645, 3514},
            new int[] {9597, -238, -8862, -6659, -5158, -1107, 8061, 306, 9183, -8105, 6794, -1413, 2088, 3005, -5682, 3325, 4325, -2226, 2076, -1902, -9521, -9629, 6639, 7156, -488, -4525, 2054, -8464, -6852, -7112, 6471, -1381, 2182, 7913, 4587, -664, -1303, 766, 6235, -9337, -6521, 1760, -9919, 1994, -1239, -2789, -728, -7697}));
  }
}
