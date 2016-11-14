package srm698;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 10/9/16. 3:54pm
 */
public class RepeatStringEasy {
  public static int maximalLength(String s) {
    if (s.length() < 2) {
      return 0;
    }

    LCSFinder lcsFinder = new LCSFinder();

    return IntStream.rangeClosed(1, s.length())
        .map(i -> 2 * lcsFinder.find(s.substring(0, i), s.substring(i)).length())
        .max()
        .getAsInt();
  }

  public static class LCSFinder {
    Map<UnorderedPair<String>, String> cache = new HashMap<>();

    public String find(String s1, String s2) {
      return cache.computeIfAbsent(
          new UnorderedPair<>(s1, s2),
          pair -> findExpensive(pair.left, pair.right));
    }

    private String findExpensive(String s1, String s2) {
      if (s1.length() < 1 || s2.length() < 1) {
        return "";
      } else if (s1.charAt(0) == s2.charAt(0)) {
        return s1.charAt(0) + find(s1.substring(1), s2.substring(1));
      } else {
        return max(find(s1.substring(1), s2), find(s1, s2.substring(1)));
      }
    }
  }

  private static String max(String s1, String s2) {
    return s1.length() > s2.length() ? s1 : s2;
  }

  public static class UnorderedPair<T extends Comparable<T>> {
    public final T left, right;

    public UnorderedPair(T a, T b) {
      if (a.compareTo(b) > 0) {
        left = a;
        right = b;
      } else {
        right = a;
        left = b;
      }
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof UnorderedPair
          && ((UnorderedPair) o).left.equals(left)
          && ((UnorderedPair) o).right.equals(right);
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  public static class RepeatStringEasyTest {
    @Test
    public void test0() {
      assertEquals(4, maximalLength("frankfurt"));
    }

    @Test
    public void test1() {
      assertEquals(0, maximalLength("single"));
    }

    @Test
    public void test2() {
      assertEquals(6, maximalLength("singing"));
    }

    @Test
    public void test3() {
      assertEquals(18, maximalLength("aababbababbabbbbabbabb"));
    }

    @Test
    public void test4() {
      assertEquals(0, maximalLength("x"));
    }

    @Test
    public void test5() {
      assertEquals(20, maximalLength("ananannnannandadnadndfsddddssdsdddnsndtttnennddnf"));
    }
  }

  public static class LcsTest {
    @Test
    public void test0() {
      assertEquals("abc", new LCSFinder().find("abc", "abc"));
    }

    @Test
    public void test1() {
      assertEquals("abc", new LCSFinder().find("123abcdef", "xyabcz"));
    }

    @Test
    public void test2() {
      assertEquals("abc", new LCSFinder().find("xyza1bb2c3", "abc"));
    }

    @Test
    public void test3() {
      assertEquals("abc", new LCSFinder().find("xyza1bb2c3", "daebfcg"));
    }
  }
}