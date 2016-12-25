import org.junit.Test;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Started: 12/25/2016 5:01pm
 * Finished: 12/25/2016 5:44pm
 */
public class FlippingTheMatrix {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numQueries = scanner.nextInt();
    Stream.generate(() -> {
      int n = scanner.nextInt();
      int[][] matrix = new int[2 * n][2 * n];

      for (int i = 0; i < 2 * n; i++) {
        for (int j = 0; j < 2 * n; j++) {
          matrix[i][j] = scanner.nextInt();
        }
      }

      return maxQuadrantSum(matrix);
    }).forEach(System.out::println);
  }

  private static long maxQuadrantSum(int[][] matrix) {
    final int width = matrix.length;
    final int height = matrix.length == 0 ? 0 : matrix[0].length;

    return IntStream.range(0, width / 2)
        .map(i -> IntStream.range(0, height / 2)
            .map(j -> max(
                getValueAtQuadrant(matrix, i, j, 0),
                getValueAtQuadrant(matrix, i, j, 1),
                getValueAtQuadrant(matrix, i, j, 2),
                getValueAtQuadrant(matrix, i, j, 3)))
            .sum())
        .sum();
  }

  private static int max(int a, int b, int c, int d) {
    return Math.max(a, Math.max(b, Math.max(c, d)));
  }

  private static int getValueAtQuadrant(int[][] matrix, int x, int y, int quadrant) {
    final int width = matrix.length;
    final int height = matrix.length == 0 ? 0 : matrix[0].length;

    if (x < 0 || x >= width / 2) {
      throw new IndexOutOfBoundsException();
    } else if (y < 0 || y >= height / 2) {
      throw new IndexOutOfBoundsException();
    }

    switch(quadrant) {
      case 0:
        return matrix[x][y];
      case 1:
        return matrix[x][height - y - 1];
      case 2:
        return matrix[width - x - 1][y];
      case 3:
        return matrix[width - x - 1][height - y - 1];
      default:
        throw new IllegalArgumentException("Quadrant must be between 0 and 3");
    }
  }

  public static class FlippingTheMatrixTest {
    @Test
    public void test0() {
      int[][] matrix = new int[][] {
          new int[] {112, 42, 83, 119},
          new int[] {56, 125, 56, 49},
          new int[] {15, 78, 101, 43},
          new int[] {62, 98, 114, 108}
      };

      assertEquals(414, maxQuadrantSum(matrix));
    }
  }
}
