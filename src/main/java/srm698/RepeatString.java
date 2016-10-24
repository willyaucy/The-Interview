package srm698;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 10/1/16.
 */
public class RepeatString {
  public static int minimalModify(String s) {
    int minSteps = Integer.MAX_VALUE;

    for (int i = 0; i <= s.length(); i++) {
      minSteps = Math.min(
          minSteps,
          editDistance(s.substring(0, i), s.substring(i)));
    }

    return minSteps;
  }

  public static int editDistance(String s1, String s2) {
    int[][] distances = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); i++) {
      distances[i][0] = i;
    }

    for (int k = 0; k <= s2.length(); k++) {
      distances[0][k] = k;
    }

    for (int i = 1; i <= s1.length(); i++) {
      for (int k = 1; k <= s2.length(); k++) {
        if (s1.charAt(i - 1) == s2.charAt(k - 1)) {
          distances[i][k] = distances[i - 1][k - 1];
        } else {
          distances[i][k] = min(
              distances[i - 1][k] + 1,
              distances[i][k - 1] + 1,
              distances[i - 1][k - 1] + 1
          );
        }
      }
    }

    return distances[s1.length()][s2.length()];
  }

  public static int min(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }

  public static class RepeatStringTest {

    @Test
    public void t1() {
      assertEquals(1, minimalModify("aba"));
    }

    @Test
    public void t2() {
      assertEquals(1, minimalModify("adam"));
    }

    @Test
    public void t3() {
      assertEquals(1, minimalModify("x"));
    }

    @Test
    public void t4() {
      assertEquals(3, minimalModify("aaabbbaaaccc"));
    }

    @Test
    public void t5() {
      assertEquals(6, minimalModify("repeatstring"));
    }

    @Test
    public void t6() {
      assertEquals(0, minimalModify("aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void t7() {
      assertEquals(3, minimalModify("xyabcdefghijklmnopqrstuvwabcdefghijklmnopqrstuvwz"));
    }
  }
}
