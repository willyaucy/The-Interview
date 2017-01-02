import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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

    Stream.of(inputs).map(SherlockAndCost::maxS).forEach(System.out::println);
  }

  public static int maxS(int[] b) {
    return maxS(IntStream.of(b).boxed().collect(Collectors.toList()));
  }

  public static int maxS(List<Integer> b) {
    if (b.size() < 2) {
      return 0;
    }

    return Math.max(
        maxS(1, b.subList(1, b.size())),
        maxS(b.get(0), b.subList(1, b.size())));
  }

  public static int maxS(int leftPrefix, List<Integer> b) {
    if (b.size() < 1) {
      return 0;
    } else if (b.size() == 1) {
      return Math.abs(b.get(0) - leftPrefix);
    }

    return Math.max(
        Math.abs(leftPrefix - 1) + maxS(1, b.subList(1, b.size())),
        Math.abs(leftPrefix - b.get(0)) + maxS(b.get(0), b.subList(1, b.size())));
  }

  private static int calculateS(int[] a) {
    return IntStream.range(1, a.length).map(i -> Math.abs(a[i] - a[i - 1])).sum();
  }

  public static class SherlockAndCostTest {
    @Test
    public void test0() {
      assertEquals(36, maxS(new int[] {10, 1, 10, 1, 10}));
    }

    @Test
    public void test1() {
      assertEquals(36, maxS(new int[] {10, 10, 10, 10, 10}));
    }
  }
}
