//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by yau on 9/14/16.
// */
//public class ChessMetric {
//  public static long howMany(int size, int[] start, int[] end, int numMoves) {
//    Map<String, Integer> positionsAtMove = new HashMap<>();
//    positionsAtMove.put(serializePoint(start), 1);
//
//    for (int numMovesRemaining = numMoves; numMovesRemaining >= 0; numMovesRemaining--) {
//      positionsAtMove = positionsAtMove
//          .entrySet()
//          .map(position -> Arrays.stream(position.split(" ")).mapToInt(Integer::parseInt).toArray())
//          .map(position -> getSuccessors(position))
//          .flatMap(successors -> Arrays.stream(successors))
//          .map(position -> serializePoint(position))
//          .distinct()
//          .collect(Collectors.toSet());
//    }
//
//    return positionsAtMove.
//  }
//
//  public static class Pair<A, B> implements Comparable {
//    public A left;
//    public B right;
//
//    public Pair(A left, B right) {
//      this.left = left;
//      this.right = right;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//      if (o instanceof Pair) {
//        Pair otherPair = (Pair)o;
//
//
//      } else {
//        return false;
//      }
//    }
//  }
//
//  public static int[][] getSuccessors(int[] point) {
//    return new int[][] {
//        new int[] { point[0] + 1, point[1] },
//        new int[] { point[0], point[1] + 1 },
//        new int[] { point[0] - 1, point[1] },
//        new int[] { point[0], point[1] - 1 },
//        new int[] { point[0] + 1, point[1] + 1 },
//        new int[] { point[0] - 1, point[1] - 1 },
//        new int[] { point[0] + 1, point[1] - 1 },
//        new int[] { point[0] - 1, point[1] + 1 },
//        new int[] { point[0] + 2, point[1] + 1 },
//        new int[] { point[0] - 2, point[1] - 1 },
//        new int[] { point[0] + 2, point[1] - 1 },
//        new int[] { point[0] - 2, point[1] + 1 },
//        new int[] { point[0] + 1, point[1] + 2 },
//        new int[] { point[0] - 1, point[1] - 2 },
//        new int[] { point[0] + 1, point[1] - 2 },
//        new int[] { point[0] - 1, point[1] + 2 },
//    };
//  }
//
//  public static boolean isEqual(int[] a, int[] b) {
//    return a[0] == b[0] && a[1] == b[1];
//  }
//
//
//  public static class ChessMetricTest {
//    @Test
//    public void t1() {
//      assertEquals(
//          1,
//          howMany(
//              3,
//              new int[] {0,0},
//              new int[] {1,0},
//              1
//          )
//      );
//    }
//
//    @Test
//    public void t2() {
//      assertEquals(
//          1,
//          howMany(
//              3,
//              new int[] {0,0},
//              new int[] {1,2},
//              1
//          )
//      );
//    }
//
//    @Test
//    public void t3() {
//      assertEquals(
//          0,
//          howMany(
//              3,
//              new int[] {0,0},
//              new int[] {2,2},
//              1
//          )
//      );
//    }
//
//    @Test
//    public void t4() {
//      assertEquals(
//          5,
//          howMany(
//              3,
//              new int[] {0,0},
//              new int[] {0,0},
//              2
//          )
//      );
//    }
//
//    @Test
//    public void t5() {
//      assertEquals(
//          243097320072600L,
//          howMany(
//              100,
//              new int[] {0,0},
//              new int[] {0,99},
//              50
//          )
//      );
//    }
//  }
//}
