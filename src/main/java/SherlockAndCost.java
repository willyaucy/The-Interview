import org.junit.Test;

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
    if (b.length < 2) {
      return 0;
    }

    long maxSEndsWith1 = Math.abs(b[0] - 1);
    long maxSEndsWithBi = Math.max(Math.abs(1 - b[1]), Math.abs(b[0] - b[1]));

    for (int i = 2; i < b.length; i++) {
      long newMaxSEndsWith1 = Math.max(
          maxSEndsWith1,
          maxSEndsWithBi + Math.abs(b[i - 1] - 1));

      long newMaxSEndsWithBi = Math.max(
          maxSEndsWith1 + Math.abs(1 - b[i]),
          maxSEndsWithBi + Math.abs(b[i - 1] - b[i]));

      maxSEndsWith1 = newMaxSEndsWith1;
      maxSEndsWithBi = newMaxSEndsWithBi;
    }

    return Math.max(maxSEndsWith1, maxSEndsWithBi);
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
