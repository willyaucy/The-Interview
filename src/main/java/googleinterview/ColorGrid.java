package googleinterview;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Given a color grid, find the perimeter of a shape.
 *
 * Concretely, the color grid is represented as a 2d array, each cell represents a pixel in the
 * image. The value of each cell ranges from 0 to 255.
 *
 * The shape at point x,y is defined as the set of all cells connected to x,y with the same color.
 *
 * Started: Oct 23 6:20PM
 * Finished: Oct 23 6:51PM (wrong answer)
 * Finished: Oct 23 7:11PM (correct answer)
 */
public class ColorGrid {
  public static int findPerimeter(int x, int y, int[][] image) {
    if (image == null || image.length == 0 ||
        x < 0 || x >= image.length ||
        y < 0 || y > image[0].length) {

      throw new IllegalArgumentException();
    }

    int perimeter = 0;
    boolean[][] visited = new boolean[image.length][image[0].length];
    Point[] pointsToVisit = new Point[] { new Point(x, y) };

    while (pointsToVisit.length > 0) {
      for (Point point : pointsToVisit) {
        visited[point.x][point.y] = true;

        if (point.x + 1 >= image.length || image[x][y] != image[point.x + 1][point.y]) {
          perimeter++;
        }

        if (point.x - 1 < 0 || image[x][y] != image[point.x - 1][point.y]) {
          perimeter++;
        }

        if (point.y + 1 >= image[0].length || image[x][y] != image[point.x][point.y + 1]) {
          perimeter++;
        }

        if (point.y - 1 < 0 || image[x][y] != image[point.x][point.y - 1]) {
          perimeter++;
        }
      }

      pointsToVisit = findPointsToExpand(pointsToVisit, image, visited);
    }

    return perimeter;
  }

  private static Point[] findPointsToExpand(Point[] currentNodes, int[][] image, boolean[][] visited) {
    int color = image[currentNodes[0].x][currentNodes[0].y];

    return Arrays.stream(currentNodes)
        .flatMap(point -> Stream.of(
            new Point(point.x + 1, point.y),
            new Point(point.x - 1, point.y),
            new Point(point.x, point.y + 1),
            new Point(point.x, point.y - 1)
        ))
        .filter(point ->
            point.x >= 0 && point.y >= 0 &&
            point.x < image.length && point.y < image[0].length &&
            color == image[point.x][point.y] &&
            !visited[point.x][point.y])
        .distinct()
        .toArray(Point[]::new);
  }

  private static class Point {
    public final int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Point && ((Point) o).x == x && ((Point) o).y == y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static class ColorGridTest {
    @Test
    public void test1() {
      assertEquals(4, findPerimeter(0, 0, new int[][] { new int[] {1} }));
    }

    @Test
    public void test2() {
      assertEquals(6, findPerimeter(0, 0, new int[][] { new int[] {1, 1} }));
    }

    @Test
    public void test3() {
      assertEquals(
          8,
          findPerimeter(
              1, 1,
              new int[][] {
                  new int[] {1, 1},
                  new int[] {1, 1}
              }));
    }

    @Test
    public void test4() {
      assertEquals(
          26,
          findPerimeter(
              3, 3,
              new int[][] {
                  new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 0, 0, 0, 0, 0, 0, 0}
              }));
    }

    @Test
    public void test5() {
      assertEquals(
          28,
          findPerimeter(
              3, 3,
              new int[][] {
                  new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 1, 1, 1, 0},
                  new int[] {0, 1, 1, 1, 0, 1, 1, 0},
                  new int[] {0, 0, 0, 0, 0, 0, 0, 0}
              }));
    }
  }
}
