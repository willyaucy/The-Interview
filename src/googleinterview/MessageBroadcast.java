package googleinterview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Imagine we have a lot of cities, and we want to broadcast the message from a city to everywhere else.
 * Each city is represented as an integer ranging from 0 to n.
 * The cost of sending the message from i to j is costs[i][j], where costs is a square matrix.
 * Find the minimum cost of sending the message from one location to all other cities.
 *
 *
 * Started Oct 23 3:10PM
 * Finished Oct 23 3:52PM
 *
 * Approach:
 * Kruskal's algorithm. Turns out they want me to study CS61B and memorize Kruskal. lololol
 */
public class MessageBroadcast {
  /**
   * Finds the minimum cost of broadcasting a message from one origin to all other cities.
   * @param origin the origin ranging from 0 to n inclusive.
   * @param costs a 2d matrix representing the cost of sending a message from one location to another.
   * @return the cost
   */
  public static int broadcast(int origin, int[][] costs) {
    List<Edge> edges = new ArrayList<>();

    for (int i = 0; i < costs.length; i++) {
      for (int j = i + 1; j < costs.length; j++) {
        edges.add(new Edge(i, j, costs[i][j]));
      }
    }

    edges.sort((e1, e2) -> e1.cost - e2.cost);

    boolean[][] isConnected = new boolean[costs.length][costs.length];
    int minimumCost = 0;

    for (Edge edge : edges) {
      if (!hasPath(edge.from, edge.to, isConnected)) {
        minimumCost += edge.cost;
        isConnected[edge.from][edge.to] = true;
        isConnected[edge.to][edge.from] = true;
      }
    }

    return minimumCost;
  }

  public static boolean hasPath(int i1, int i2, boolean[][] graph) {
    LinkedList<Integer> nodesToExpand = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    nodesToExpand.offer(i1);

    while (!nodesToExpand.isEmpty()) {
      int node = nodesToExpand.poll();
      visited.add(node);

      if (node == i2) {
        return true;
      } else {
        for (int i = 0; i < graph.length; i++) {
          if (graph[node][i] && !visited.contains(i)) {
            nodesToExpand.offer(i);
          }
        }
      }
    }

    return false;
  }

  private static class Edge {
    final int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
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
