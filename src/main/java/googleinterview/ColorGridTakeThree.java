package googleinterview;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
 * Started: Nov 6 4:55PM
 * Finished: Nov 6 5:20PM
 */
public class ColorGridTakeThree {
  public static int findPerimeter(int x, int y, int[][] image) {
    boolean[][] visited = new boolean[image.length][image[0].length];
    int perimeter = 0;

    Set<Point> pointsToVisit = Collections.singleton(new Point(x, y));

    while (!pointsToVisit.isEmpty()) {
      Set<Point> pointsToVisitNext = new HashSet<>();

      for (Point point : pointsToVisit) {
        perimeter += findPerimeterAtCell(point.x, point.y, image);
        visited[point.x][point.y] = true;
      }

      for(Point point : pointsToVisit) {
        pointsToVisitNext.addAll(findNewConnectedPointsFromCell(point.x, point.y, image, visited));
      }

      pointsToVisit = pointsToVisitNext;
    }

    return perimeter;
  }

  private static Set<Point> findNewConnectedPointsFromCell(int x, int y, int[][] image, boolean[][] visited) {
    return Stream.of(new Point(x + 1, y), new Point(x - 1, y), new Point(x, y + 1), new Point(x, y - 1))
        .filter(point ->
            point.x >= 0 && point.x < image.length &&
            point.y >= 0 && point.y < image[0].length &&
            !visited[point.x][point.y] &&
            image[x][y] == image[point.x][point.y])
        .distinct()
        .collect(Collectors.toSet());
  }

  private static int findPerimeterAtCell(int x, int y, int[][] image) {
    int perimeter = 0;

    if (x - 1 < 0 || image[x][y] != image[x - 1][y]) perimeter++;
    if (x + 1 >= image.length || image[x][y] != image[x + 1][y]) perimeter++;
    if (y - 1 < 0 || image[x][y] != image[x][y - 1]) perimeter++;
    if (y + 1 >= image[0].length || image[x][y] != image[x][y + 1]) perimeter++;

    return perimeter;
  }

  private static class Point {
    private final int x, y;

    public Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object o) {
      return o instanceof Point && ((Point) o).x == x && ((Point) o).y == y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static class ColorGridTakeThreeTest {
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
