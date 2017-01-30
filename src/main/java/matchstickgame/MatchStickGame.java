package matchstickgame;

/**
 You and your friend are playing a simple matchstick game with a pile of m matches.
 You start the game by removing as many matches as you like, except that you must take
 at least one and must leave at least one match.

 Thereafter, each player in turn must
 remove at least one match and at most twice the number of matches his opponent just took.
 The player who removes the last match wins. Your goal is to find the best strategy to win
 this game, or to determine whether it is not possible to win.

 6 true
 > 1    2           3    4    5
 < 1 2  1 2 3 4
 >
 */

public class MatchStickGame {
  public static void main(String[] args) {
    System.out.println(canWin(1));
    System.out.println(canWin(2));
    System.out.println(canWin(3));
    System.out.println(canWin(4));
    System.out.println(canWin(5));
    System.out.println(canWin(6));
    System.out.println(canWin(7));
    System.out.println(canWin(8));
    System.out.println(canWin(9));
    System.out.println(canWin(10));
    System.out.println(canWin(11));
    System.out.println(canWin(12));
    System.out.println(canWin(13));
    System.out.println(canWin(14));

    for (int i = 1; i <= 20; i++) {
      if( canWin(i) != canWinDp(i)) {
        System.out.println("NO Match for " + i);
      }
    }
  }

  public static boolean canWin(int m) {
    return canWin(m, m - 1);
  }

  public static boolean canWin(int m, int maxToTakeOut) {
    if (m == 1) {
      return true;
    }

    for (int i = 1; i <= Math.min(m, maxToTakeOut); i++) {
      if (!canWin(m - i, i * 2)) {
        return true;
      }
    }

    return false;
  }

  public static boolean canWinDp(int m) {
    if (m == 1) {
      return true;
    }
        
    /*
     * 1st dimension: m - 1
     * 2nd dimension: maxToTakeOut - 1;
     */
    boolean[][] dp = new boolean[m + 1][(m + 1) * 2];

    dp[1][1] = true;

    for (int mm = 2; mm <= m; mm++) {
      for (int i = 1; i <= (m == mm ? mm - 1 : mm); i++) {
        boolean canWin = !dp[mm - i][Math.max(1, Math.min(mm - i, i * 2))];

        if (canWin) {
          for (int j = i; j <= mm; j++) {
            dp[mm][j] = canWin;
          }

          break;
        }
      }
    }

    for (int i = 0; i < dp[0].length; i++) {
      if (dp[m][i]) {
        return true;
      }
    }

    return false;
  }
}