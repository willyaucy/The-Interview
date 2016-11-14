package lis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Given a list of integers, return the longest increasing subsequence.
 */
public class LongestIncreasingSubsequence {
  public static List<Integer> lis(List<Integer> list) {
    List<List<Integer>> subsequences = new ArrayList<>(list.size());

    for (int i : list) {
      List<Integer> lisWithoutI = subsequences.stream()
          .filter(subseq -> subseq.get(subseq.size() - 1) <= i)
          .reduce((subseq1, subseq2) -> subseq1.size() >= subseq2.size() ? subseq1 : subseq2)
          .orElse(Collections.emptyList());

      List<Integer> lisWithI = new ArrayList<>(lisWithoutI.size() + 1);
      lisWithI.addAll(lisWithoutI);
      lisWithI.add(i);

      subsequences.add(lisWithI);
    }

    return subsequences.stream()
        .reduce((subseq1, subseq2) -> subseq1.size() >= subseq2.size() ? subseq1 : subseq2)
        .orElse(Collections.emptyList());
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

    @Test
    public void test4() {
      assertEquals(6, lis(Arrays.asList(15, 27, 14, 38, 26, 55, 46, 65, 85)).size());
    }

    @Test
    public void test5() {
      assertArrayEquals(
          new int[] {15, 27, 38, 55, 65, 85},
          lis(Arrays.asList(15, 27, 14, 38, 26, 55, 46, 65, 85)).stream().mapToInt(i -> i).toArray());
    }
  }
}
