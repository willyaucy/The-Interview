package lis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Given a list of integers, return the longest increasing subsequence.
 */
public class LongestIncreasingSubsequence {
  public static List<Integer> lis(List<Integer> list) {
    List<List<Integer>> dp = list.stream().reduce(
        new ArrayList<List<Integer>>(),
        (increasingSubseqs, i) -> {
          List<Integer> lisBefore = increasingSubseqs.stream()
              .filter(subseq -> subseq.get(subseq.size() - 1) <= i.intValue())
              .reduce((subseq1, subseq2) -> subseq1.size() > subseq2.size() ? subseq1 : subseq2)
              .orElse(Collections.emptyList());

          List<Integer> output = new ArrayList<>();
          output.addAll(lisBefore);
          output.add(i);

          increasingSubseqs.add(output);

          return increasingSubseqs;
        },
        (list1, list2) -> { throw new IllegalStateException(); });

    return dp.stream().reduce((l1, l2) -> l1.size() > l2.size() ? l1 : l2).orElse(Collections.emptyList());
  }

  public static class LongestIncreasingSubsequenceTest {
    @Test
    public void test0() {
      assertEquals(5, lis(Arrays.asList(1, 2, 3, 4, 5)).size());
    }

    @Test
    public void test1() {
      assertEquals(4, lis(Arrays.asList(1, 2, 3, 4, 0, 1, 2)).size());
    }

    @Test
    public void test2() {
      assertEquals(5, lis(Arrays.asList(1, 90, 2, 90, 3, 90, 4, 5)).size());
    }

    @Test
    public void test3() {
      assertEquals(1, lis(Arrays.asList(5, 4, 3, 2, 1, 0)).size());
    }
  }
}
