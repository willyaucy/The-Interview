import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/subsets-ii
 */
public class SubsetsII {
  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> output = new LinkedList<>();
    output.add(Collections.emptyList());

    int i = 0;

    while (i < nums.length) {
      int repeatingInteger = nums[i];
      int repeatCount = 1;

      List<List<Integer>> subsetsToAdd = new LinkedList<>();

      while (i < nums.length && nums[i] == repeatingInteger) {
        for (List<Integer> subset : output) {
          List<Integer> newSubset = new LinkedList<>(subset);
          newSubset.addAll(IntStream.generate(() -> repeatingInteger)
              .limit(repeatCount).boxed().collect(Collectors.toList()));

          subsetsToAdd.add(newSubset);
        }

        i++;
        repeatCount++;
      }

      output.addAll(subsetsToAdd);
    }

    return output;
  }

  public static class SubsetsIITest {
    @Test
    public void test0() {
      assertEquals(Collections.singletonList(Collections.emptyList()), subsetsWithDup(new int[] {}));
    }
  }

  @Test
  public void test1() {
    assertEquals(
        Collections.singletonList(Collections.emptyList()),
        subsetsWithDup(new int[] {1, 2, 2}));
  }
}
