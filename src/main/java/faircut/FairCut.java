package faircut;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/fair-cut
 * Started: 1/4/17 4:28pm
 */
public class FairCut {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    final int n = scanner.nextInt();
    final int k = scanner.nextInt();
    final int[] a = IntStream.generate(scanner::nextInt).limit(n).toArray();

    System.out.println(minUnfairness(a, k));
  }

  public static long minUnfairness(int[] a, int k) {
    return -1;
  }

  public static class FairCutTest {
    @Test
    public void test0() {
      assertEquals(6, minUnfairness(new int[] {4, 3, 1, 2}, 2));
    }

    @Test
    public void test1() {
      assertEquals(2, minUnfairness(new int[] {3, 3, 3, 1}, 1));
    }
  }
}
