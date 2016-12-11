import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringMerger {
  public static class StringTriple {
    public final String left, middle, right;

    public StringTriple(String left, String middle, String right) {
      this.left = left;
      this.middle = middle;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof StringTriple
          && left.equals(((StringTriple) o).left)
          && middle.equals(((StringTriple) o).middle)
          && right.equals(((StringTriple) o).right);
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, middle, right);
    }
  }

  private final Map<StringTriple, Boolean> memo;

  private StringMerger() {
    memo = new HashMap<>();
  }

  private boolean isMergeInternal(String s, String part1, String part2) {
    if (s.length() == 0) {
      return part1.length() == 0 && part2.length() == 0;
    } else if (s.length() != part1.length() + part2.length()) {
      return false;
    } else if ((part1.length() == 0 || s.charAt(0) != part1.charAt(0))
        && (part2.length() == 0 || s.charAt(0) != part2.charAt(0))) {
      return false;
    }

    return memo.computeIfAbsent(new StringTriple(s, part1, part2), triple -> {
      if (part1.length() != 0 && s.charAt(0) == part1.charAt(0) &&
          isMergeInternal(s.substring(1), part1.substring(1), part2)) {
        return true;
      } else if (part2.length() != 0 && s.charAt(0) == part2.charAt(0) &&
          isMergeInternal(s.substring(1), part1, part2.substring(1))) {
        return true;
      } else {
        return false;
      }
    });
  }

  public static boolean isMerge(String s, String part1, String part2) {
    return new StringMerger().isMergeInternal(s, part1, part2);
  }

  public static class StringMergerTest {
    @Test
    public void test0() {
      assertTrue("codewars can be created from code and wars", StringMerger.isMerge("codewars", "code", "wars"));
      assertTrue("codewars can be created from cdwr and oeas", StringMerger.isMerge("codewars", "cdwr", "oeas"));
      assertFalse("Codewars are not codwars", StringMerger.isMerge("codewars", "cod", "wars"));
    }
  }
}