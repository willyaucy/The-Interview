package combinationsum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yauyin on 1/6/17.
 */
public class CombinationSumTakeTwo {
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    return new CombinationSumCalculator(candidates).calculate(target, 0);
  }

  private static class Pair<L, R> {
    public final L left;
    public final R right;

    public Pair(L left, R right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Pair
          && Objects.equals(((Pair) o).left, left)
          && Objects.equals(((Pair) o).right, right);
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  private static class CombinationSumCalculator {
    private final Map<Pair<Integer, Integer>, List<List<Integer>>> memo;
    private final int[] candidates;

    private CombinationSumCalculator(int[] candidates) {
      this.memo = new HashMap<>();
      this.candidates = candidates;
    }

    private List<List<Integer>> calculate(int target, int candidatesOffset) {
      return memo.computeIfAbsent(new Pair<>(target, candidatesOffset), inputPair -> {
        if (target == 0) {
          return Collections.singletonList(Collections.emptyList());
        } else if (target < 0 || candidates.length - candidatesOffset <= 0) {
          return Collections.emptyList();
        }

        List<List<Integer>> combinations = new LinkedList<>();

        combinations.addAll(
            calculate(target, candidatesOffset + 1));

        combinations.addAll(
            calculate(target - candidates[candidatesOffset], candidatesOffset).stream()
                .map(combination ->
                    Stream.concat(
                        Stream.of(candidates[candidatesOffset]),
                        combination.stream()).collect(Collectors.toList()))
                .collect(Collectors.toList()));

        return combinations;
      });
    }
  }

  public static class CombinationSumTakeTwoTest {
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
