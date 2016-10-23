package googleinterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Imagine we have a lot of cities, and we want to broadcast the message from a city to everywhere else.
 * Each city is represented as an integer ranging from 0 to n.
 * The cost of sending the message from i to j is costs[i][j], where costs is a square matrix.
 * Find the minimum cost of sending the message from one location to all other cities.
 */
public class MessageBroadcast {
  /**
   * Finds the minimum cost of broadcasting a message from one origin to all other cities.
   * @param origin the origin ranging from 0 to n inclusive.
   * @param costs a 2d matrix representing the cost of sending a message from one location to another.
   * @return the cost
   */
  public static int broadcast(int origin, int[][] costs) {
    return -1;
  }

  public static class MessageBroadcastTest {
    @Test
    public void test1() {
      assertEquals(
          1,
          broadcast(
              0,
              new int[][]{
                  new int[]{1, 1},
                  new int[]{1, 1}
              }));
    }
    @Test
    public void test2() {
      assertEquals(
          4,
          broadcast(
              1,
              new int[][]{
                  new int[]{1, 1, 1, 1, 1},
                  new int[]{1, 1, 1, 1, 1},
                  new int[]{1, 1, 1, 1, 1},
                  new int[]{1, 1, 1, 1, 1},
                  new int[]{1, 1, 1, 1, 1}
              }));
    }
  }
}
