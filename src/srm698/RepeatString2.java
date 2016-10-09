package srm698;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class RepeatString2 {
  public static int minimalModify(String s) {
    return IntStream.rangeClosed(0, s.length())
        .map(i -> distance(s.substring(0, i), s.substring(i)))
        .min()
        .getAsInt();
  }

  public static int distance(String s1, String s2) {
    int[][] minEdits = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i < s1.length() + 1; i++) {
      minEdits[i][0] = i;
    }

    for (int i = 0; i < s2.length() + 1; i++) {
      minEdits[0][i] = i;
    }

    for (int i = 1; i < s1.length() + 1; i++) {
      for (int j = 1; j < s2.length() + 1; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          minEdits[i][j] = minEdits[i - 1][j - 1];
        } else {
          minEdits[i][j] = min(
              minEdits[i - 1][j] + 1,
              minEdits[i][j - 1] + 1,
              minEdits[i - 1][j - 1] + 1);
        }
      }
    }

    return minEdits[s1.length()][s2.length()];
  }

  public static int min(int i1, int i2, int i3) {
    return Math.min(Math.min(i1, i2), i3);
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
