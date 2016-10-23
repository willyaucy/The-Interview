package srm698;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 10/9/16. 3:54pm
 */
public class RepeatStringEasy {
  public static int maximalLength(String s) {
    if (s.length() < 2) {
      return 0;
    }

    // subproblem   : what is the best subsequence ending at ij
    // 1st dimension: i
    // 2nd dimension: j
    // 3rd dimension: (best subsequence ending at ij; best subsequence ending at xy, where x <= i and y <= j)
    int[][][] dp = new int[s.length()][s.length()][2];

    for (int j = 1; j < s.length(); j++) {
      dp[0][j][0] = s.charAt(0) == s.charAt(j) ? 2 : 0;
      dp[0][j][1] = Math.max(dp[0][j - 1][1], dp[0][j][0]);
    }

    for (int j = 2; j < s.length(); j++) {
      for (int i = 1; i < j; i++) {
        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);

        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j][0] = dp[i - 1][j - 1][1] + 2;
          dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j][0]);
        }
      }
    }

    return dp[s.length() - 2][s.length() - 1][1];
  }

  public static class RepeatStringEasyTest {
    @Test
    public void test0() {
      assertEquals(4, maximalLength("frankfurt"));
    }

    @Test
    public void test1() {
      assertEquals(0, maximalLength("single"));
    }

    @Test
    public void test2() {
      assertEquals(6, maximalLength("singing"));
    }

    @Test
    public void test3() {
      assertEquals(18, maximalLength("aababbababbabbbbabbabb"));
    }

    @Test
    public void test4() {
      assertEquals(0, maximalLength("x"));
    }
  }
}