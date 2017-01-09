package combinationsum;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Started: 6:59pm
 */
public class CombinationSumII {
  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> combinations =
        Stream.iterate(BigInteger.ZERO, previousSet -> previousSet.add(BigInteger.ONE))
            .limit(BigInteger.valueOf(2).pow(candidates.length).longValueExact())
            .map(set -> IntStream.range(0, candidates.length)
                .filter(set::testBit)
                .mapToObj(idx -> candidates[idx])
                .collect(Collectors.toList()))
            .filter(set -> set.stream().mapToInt(i -> i).sum() == target)
            .collect(Collectors.toList());

    return IntStream.range(0, combinations.size())
        .mapToObj(idx -> new Pair<>(idx > 0 ? combinations.get(idx - 1) : null, combinations.get(idx)))
        .filter(pair -> !Arrays.equals(pair.left == null ? null : pair.left.toArray(), pair.right.toArray()))
        .map(pair -> pair.right)
        .collect(Collectors.toList());
  }

  private static class Pair<L, R> {
    public final L left;
    public final R right;

    private Pair(L left, R right) {
      this.left = left;
      this.right=  right;
    }
  }

  public static class CombinationSumIITest {
    @Test
    public void test0() {
      System.out.println(combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
    }
  }
}