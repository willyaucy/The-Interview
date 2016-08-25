package srm680;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/25/16.
 */
public class BearPair {
  public static int bigDistance(String s) {
    int bestLeftSolution = -1;

    for (int i = s.length() - 1; i > 0; i--) {
      if (s.charAt(i) != s.charAt(0)) {
        bestLeftSolution = i;
        break;
      }
    }

    int bestRightSolution = -1;

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1)) {
        bestRightSolution = s.length() - i - 1;
        break;
      }
    }

    return Math.max(bestLeftSolution, bestRightSolution);
  }

  public static class BearPairTest {
    @Test
    public void test1() {
      assertEquals(47, bigDistance("xxyyxyxyyyyyyxxxyxyxxxyxyxyyyyyxxxxxxyyyyyyyyxxxxx"));
    }

    @Test
    public void test2() {
      assertEquals(-1, bigDistance("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void test3() {
      assertEquals(36, bigDistance("ttpoozilzpxtjebarvgiylurwjjpoutfeodby"));
    }

    @Test
    public void test4() {
      assertEquals(1, bigDistance("tj"));
    }
  }
}
