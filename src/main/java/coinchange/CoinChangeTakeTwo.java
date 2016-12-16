package coinchange;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/coin-change
 */
public class CoinChangeTakeTwo {
  private static class IntPair {
    final int left, right;

    IntPair(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof IntPair
          && left == ((IntPair) o).left
          && right == ((IntPair) o).right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  private static class CoinChangeCalculator {
    private final int[] coins;
    private final Map<IntPair, Long> memo;

    CoinChangeCalculator(int[] coins) {
      this.coins = coins;
      memo = new HashMap<>();
    }

    long numWays(int amount, int coinsOffset) {
      if (amount == 0) {
        return 1L;
      } else if (coinsOffset >= coins.length) {
        return 0L;
      } else if (IntStream.of(coins).noneMatch(coin -> coin <= amount)) {
        return 0L;
      }

      return memo.computeIfAbsent(
          new IntPair(amount, coinsOffset),
          ignoredPair ->
              numWays(amount - coins[coinsOffset], coinsOffset) +
              numWays(amount, coinsOffset + 1));
    }
  }

  public static long numWays(int amount, int[] coins) {
    return new CoinChangeCalculator(coins).numWays(amount, 0);
  }

  public static class CoinChangeTest {
    @Test
    public void test0() {
      assertEquals(4, numWays(4, new int[] {1, 2, 3}));
    }

    @Test
    public void test1() {
      assertEquals(5, numWays(10, new int[] {2, 3, 5, 6}));
    }
  }
}
