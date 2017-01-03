import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-cost
 */
public class SherlockAndCost {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTestCases = scanner.nextInt();

    int[][] inputs = Stream.generate(() -> {
      int numElements = scanner.nextInt();
      return IntStream.generate(scanner::nextInt).limit(numElements).toArray();
    }).limit(numTestCases).toArray(int[][]::new);

    Stream.of(inputs).map(SherlockAndCost::computeMaxS).forEach(System.out::println);
  }

  public static long computeMaxS(int[] b) {
    return new MaxSFinder(b).computeMaxS();
  }

  private static final class IntPair {
    private final int left, right;

    public IntPair(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof IntPair
          && ((IntPair) o).left == this.left
          && ((IntPair) o).right == this.right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  private static final class MaxSFinder {
    private int[] b;
    private Map<IntPair, Long> memo;

    public MaxSFinder(int[] b) {
      this.b = b;
      this.memo = new HashMap<>();
    }

    public long computeMaxS() {
      if (b.length == 0) {
        return 0;
      }

      return Math.max(
          computeMaxS(0, 1),
          computeMaxS(0, b[0]));
    }

    public long computeMaxS(int startIdx, int aStart) {
      if (b.length - startIdx < 2) {
        return 0;
      } else if (b.length - startIdx == 2) {
        return Math.abs(b[startIdx + 1] - aStart);
      }

      return memo.computeIfAbsent(
          new IntPair(startIdx, aStart),
          ignoredPair -> Math.max(
              Math.abs(aStart - 1) + computeMaxS(startIdx + 1, 1),
              Math.abs(aStart - b[startIdx + 1]) + computeMaxS(startIdx + 1, b[startIdx + 1])));
    }
  }

  public static class SherlockAndCostTest {
    @Test
    public void test0() {
      assertEquals(36, computeMaxS(new int[] {10, 1, 10, 1, 10}));
    }

    @Test
    public void test1() {
      assertEquals(36, computeMaxS(new int[] {10, 10, 10, 10, 10}));
    }

    @Test
    public void test2() {
      assertEquals(6442450938L, computeMaxS(
          new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}));
    }
  }
}
