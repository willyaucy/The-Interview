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

  }

  private static class IntPair {
    final int left, right;

    IntPair(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof IntPair
          && ((IntPair) o).left == left
          && ((IntPair) o).right == right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  Map<IntPair, int[]> memo;
  final int[] a;

  private FairCut(int[] a) {
    this.memo = new HashMap<>();
    this.a = a;
  }

  public int[] calculateFairestI(int aOffset, int k) {
    if (k <= 0) {
      throw new IllegalArgumentException("k must be greater than 0");
    } else if (a.length - aOffset <= k) {
      throw new IllegalArgumentException("No enough elements in a to distribute");
    }

    return memo.computeIfAbsent(new IntPair(aOffset, k), inputIntPair -> {
      if (k == 1) {
        return new int[] {
            IntStream.range(aOffset, a.length)
                .mapToObj(iCandidate -> new IntPair(
                    iCandidate,
                    IntStream.of(a).skip(aOffset)
                        .map(aElement -> Math.abs(aElement - iCandidate)).sum()))
                .reduce((pair1, pair2) -> pair1.right <= pair2.right ? pair1 : pair2)
                .map(pair -> pair.left)
                .get()
        };
      }

      int[] x = calculateFairestI(aOffset + 1, k - 1);

      
//      int[] y = a.length - aOffset - 1 <= k
//          calculateFairestI(aOffset + 1, k);

      return new int[] {};
    });
  }

  private static long calculateUnfairness(int[] a, int[] i) {
//    return IntStream.of(i)
//        .map(iElement -> )
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
