package combinationsum;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/combination-sum/
 * Started: 5:48pm
 * Finished: 6:45pm
 */
public class CombinationSum {
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    return CombinationSumCalculator.calculate(candidates, target);
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

  private static class CombinationSumCalculator {
    private final Map<IntPair, List<List<Integer>>> memo;
    private final int[] candidates;

    private CombinationSumCalculator(int[] candidates) {
      this.memo = new HashMap<>();
      this.candidates = candidates;
    }

    private List<List<Integer>> calculateInternal(int candidatesOffset, int target) {
      return memo.computeIfAbsent(new IntPair(candidatesOffset, target), inputPair -> {
        if (candidatesOffset >= candidates.length || target < 0) {
          return Collections.emptyList();
        } else if (target == 0) {
          return Collections.singletonList(Collections.emptyList());
        } else {
          List<List<Integer>> solutionsWithCandidateAtOffset =
              calculateInternal(candidatesOffset, target - candidates[candidatesOffset])
                  .stream()
                  .map(solution ->
                      Stream.concat(Stream.of(candidates[candidatesOffset]), solution.stream())
                          .collect(Collectors.toList()))
                  .collect(Collectors.toList());

          return Stream.concat(
              solutionsWithCandidateAtOffset.stream(),
              calculateInternal(candidatesOffset + 1, target).stream())
              .collect(Collectors.toList());
        }
      });
    }

    public static List<List<Integer>> calculate(int[] candidates, int target) {
      return new CombinationSumCalculator(candidates).calculateInternal(0, target);
    }
  }

  public static class CombinationSumTest {
    @Test
    public void test0() {
      System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
    }

    @Test
    public void test1() {
      System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 4));
    }
  }
}