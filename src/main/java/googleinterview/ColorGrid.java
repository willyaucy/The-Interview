package googleinterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a color grid, find the perimeter of a shape.
 *
 * Concretely, the color grid is represented as a 2d array, each cell represents a pixel in the
 * image. The value of each cell ranges from 0 to 255.
 *
 * The shape at point x,y is defined as the set of all cells connected to x,y with the same color.
 */
public class ColorGrid {
  public static int findPerimeter(int x, int y, int[][] image) {
    return -1;
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
