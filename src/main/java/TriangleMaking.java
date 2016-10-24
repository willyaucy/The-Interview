import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/20/16.
 * Started: 08/20/2016 4pm - 4:24pm
 */
public class TriangleMaking {
  public static int maxPerimeter(int a, int b, int c) {
    int min1 = a, min2 = b, min3 = c;

    if (min1 > min2) {
      int newMin2 = min1;
      min1 = min2;
      min2 = newMin2;
    }

    if (min2 > min3) {
      int newMin3 = min2;
      min2 = min3;
      min3 = newMin3;

      if (min1 > min2) {
        int newMin2 = min1;
        min1 = min2;
        min2 = newMin2;
      }
    }

    return min1 + min2 + Math.min(min3, min1 + min2 - 1);
  }

  public static class TrangleMakingTest {
    @Test
    public void test1() {
      assertEquals(5, maxPerimeter(1,2,3));
    }
    @Test
    public void test2() {
      assertEquals(6, maxPerimeter(2,2,2));
    }
    @Test
    public void test3() {
      assertEquals(3, maxPerimeter(1,100,1));
    }
    @Test
    public void test4() {
      assertEquals(113, maxPerimeter(41,64,16));
    }
  }
}
