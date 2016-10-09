package srm698;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 10/8/16.
 */
public class RepeatStringEasy {
  public static int maximalLength(String s) {
    List<Pair> subsequences = new ArrayList<>();

    // find all subsequences of length 1
    for (int i = 0; i < s.length(); i++) {
      subsequences.add(Pair.of(i, String.valueOf(s.charAt(i))));
    }

    int maxLength = 0;

    // find all subsequences of length i
    for (int length = 2; length <= s.length(); length++) {
      subsequences = subsequences.parallelStream()
          .flatMap(subsequence -> IntStream.range(0, subsequence.getLeft())
              .mapToObj(startIndex -> Pair.of(
                  startIndex,
                  s.charAt(startIndex) + subsequence.getRight())))
          .collect(Collectors.toList());

      if (length % 2 == 0) {
        if (subsequences.parallelStream().filter(subsequence -> isSquare(subsequence.getRight())).count() > 0) {
          maxLength = length;
        } else {
          return maxLength;
        }
      }
    }

    return maxLength;
  }

  public static boolean isSquare(String s) {
    return s.substring(0, s.length() / 2).equals(s.substring(s.length() / 2));
  }

  public static class Pair {
    private int left;
    private String right;

    public static Pair of(int left, String right) {
      Pair pair = new Pair();

      pair.left = left;
      pair.right = right;

      return pair;
    }

    public int getLeft() {
      return left;
    }

    public String getRight() {
      return right;
    }
  }

  public static class RepeatStringTest {

    @Test
    public void t1() {
      assertEquals(2, maximalLength("aba"));
    }

    @Test
    public void t2() {
      assertEquals(2, maximalLength("adam"));
    }

    @Test
    public void t3() {
      assertEquals(0, maximalLength("x"));
    }

    @Test
    public void t4() {
      assertEquals(4, maximalLength("frankfurt"));
    }

    @Test
    public void t5() {
      assertEquals(6, maximalLength("singing"));
    }

    @Test
    public void t6() {
      assertEquals(0, maximalLength("single"));
    }

    @Test
    public void t7() {
      assertEquals(18, maximalLength("aababbababbabbbbabbabb"));
    }

    @Test
    public void t8() {
      assertEquals(20, maximalLength("ananannnannandadnadndfsddddssdsdddnsndtttnennddnf"));
    }

    @Test
    public void t9() {
      assertEquals(24, maximalLength("zxxxxxxxxxxxxxxxxxxxxxxfffffffffffffffffffffffff"));
    }
  }
}
