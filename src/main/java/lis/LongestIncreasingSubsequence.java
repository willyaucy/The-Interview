package lis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 11/16/16.
 */
public class LongestIncreasingSubsequence {
//  public static class IntPair {
//    public final int left, right;
//
//    public IntPair(int left, int right) {
//      this.left = left;
//      this.right = right;
//    }
//  }
//
//  public static class IntListNode {
//    public final int value;
//    public final IntListNode next;
//
//    public IntListNode(int value, IntListNode next) {
//      this.value = value;
//      this.next = next;
//    }
//  }
//
//  public static List<Integer> lis(List<Integer> list) {
//    // subsequenceTails.get(i) is the subsequence of length i with the lowest tail number seen so far.
//    List<IntListNode> subsequences = new ArrayList<>(list.size());
//
//    for (int i = 0; i < list.size(); i++) {
//      indexOfRight(subsequences.subList(0, i).);
////      list.subList(0, i).map()
//    }
//
//    return null;
//  }
//
//  public static <T> int indexOfRight(List<T> sortedList, T target, Comparator<T> comparator) {
//    int lowIndex = 0, highIndex = sortedList.size() - 1;
//
//    while (lowIndex <= highIndex) {
//      int midIndex = lowIndex + (highIndex - lowIndex) / 2;
//
//      if (comparator.compare(sortedList.get(midIndex), target) > 0) {
//
//      } else if (comparator.compare(sortedList.get(midIndex), target) < 0) {
//    }
//
//    return comparator.compare(sortedList.get(lowIndex)
//
//    int difference = comparator.compare()
//
//
//  }
//
//
//  public static class LongestIncreasingSubsequenceTest {
//    @Test
//    public void test0() {
//      assertEquals(5, lis(Arrays.asList(1, 2, 3, 4, 5)).size());
//    }
//
//    @Test
//    public void test1() {
//      assertEquals(4, lis(Arrays.asList(1, 2, 3, 4, 0, 1, 2)).size());
//    }
//
//    @Test
//    public void test2() {
//      assertEquals(5, lis(Arrays.asList(1, 90, 2, 90, 3, 90, 4, 5)).size());
//    }
//
//    @Test
//    public void test3() {
//      assertEquals(1, lis(Arrays.asList(5, 4, 3, 2, 1, 0)).size());
//    }
//
//    @Test
//    public void test4() {
//      assertEquals(6, lis(Arrays.asList(15, 27, 14, 38, 26, 55, 46, 65, 85)).size());
//    }
//
//    @Test
//    public void test5() {
//      assertArrayEquals(
//          new int[] {15, 27, 38, 55, 65, 85},
//          lis(Arrays.asList(15, 27, 14, 38, 26, 55, 46, 65, 85)).stream().mapToInt(i -> i).toArray());
//    }
//  }
}
