package coinchange;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/coin-change
 * Created by yau on 12/11/16. 3:14am
 */
public class CoinChange {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    long amount = scanner.nextInt();
    long numCoins = scanner.nextInt();
    long[] coins = LongStream.generate(scanner::nextInt).limit(numCoins).toArray();

    System.out.println(numWays(amount, coins));
  }

  private static class IntPair {
    public final long left, right;

    public IntPair(long left, long right) {
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
    private final long[] coins;
    private final Map<IntPair, Long> memo;

    public CoinChangeCalculator(long[] coins) {
      this.coins = coins;
      this.memo = new HashMap<>();
    }

    public long numWays(long amount, int coinsOffset) {
      if (amount == 0) {
        return 1;
      } else if (coinsOffset >= coins.length) {
        return 0;
      }

      return memo.computeIfAbsent(new IntPair(amount, coinsOffset), ignoredPair -> {
        long wCoinAtOffset = coins[coinsOffset] <= amount
            ? numWays(amount - coins[coinsOffset], coinsOffset)
            : 0;

        long woCoinAtOffset = numWays(amount, coinsOffset + 1);

        return wCoinAtOffset + woCoinAtOffset;
      });
    }
  }

  public static long numWays(long amount, long[] coins) {
    return new CoinChangeCalculator(coins).numWays(amount, 0);
  }

  public static class CoinChangeTest {
    @Test
    public void test0() {
      assertEquals(4, numWays(4, new long[] {1, 2, 3}));
    }

    @Test
    public void test1() {
      assertEquals(5, numWays(10, new long[] {2, 3, 5, 6}));
    }
  }
}
