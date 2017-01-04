package abbreviation;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Started: 1/3/17 06:30pm
 */
public class Abbreviation {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numQueries = scanner.nextInt();

    Stream.generate(() -> {
      String a = scanner.next();
      String b = scanner.next();

      return canAbbr(a, b);
    }).limit(numQueries).map(result -> result ? "YES" : "NO").forEach(System.out::println);
  }

  public static class Pair<L, R> {
    final L left;
    final R right;

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

  /**
   *
   * @param a The string
   * @param b The subsequence
   * @return
   */
  public static boolean canAbbr(String a, String b) {
    return new Abbreviator(a, b).canAbbr();
  }

  public static class Abbreviator {
    /**
     * Maps from (a, b) to result
     */
    final Map<Pair<String, String>, Boolean> memo;

    private final String a, b;

    public Abbreviator(String a, String b) {
      memo = new HashMap<>();
      this.a = a;
      this.b = b;
    }

    public boolean canAbbr() {
      return canAbbr(a, b);
    }

    private boolean canAbbr(String a, String b) {
      return memo.computeIfAbsent(new Pair<>(a, b), abPair -> {
        if (a.length() == b.length()) {
          return a.toUpperCase().equals(b);
        } else if (b.length() == 0) {
          return a.equals(a.toLowerCase());
        } else if (a.length() < b.length()) {
          return false;
        }

        if (Character.toUpperCase(a.charAt(0)) == b.charAt(0)) {
          if (Character.isUpperCase(a.charAt(0))) {
            return canAbbr(a.substring(1), b.substring(1));
          } else {
            return canAbbr(a.substring(1), b.substring(1)) || canAbbr(a.substring(1), b);
          }
        } else if (Character.isUpperCase(a.charAt(0))) {
          return false;
        }

        if (Character.toUpperCase(a.charAt(a.length() - 1)) == b.charAt(b.length() - 1)) {
          if (Character.isUpperCase(a.charAt(a.length() - 1))) {
            return canAbbr(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1));
          } else {
            return canAbbr(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1))
                || canAbbr(a.substring(0, a.length() - 1), b);
          }
        } else if (Character.isUpperCase(a.charAt(a.length() - 1))) {
          return false;
        }

        return canAbbr(a.substring(1, a.length() - 1), b);
      });
    }
  }

  public static class AbbreviationTest {
    @Test
    public void test0() {
      assertTrue(canAbbr("daBcd", "ABC"));
    }

    @Test
    public void test1() {
      assertTrue(canAbbr("daBbcd", "ABC"));
    }

    @Test
    public void test2() {
      assertTrue(canAbbr("dabbbbbBbcd", "ABC"));
    }
    @Test
    public void test3() {
      assertFalse(canAbbr("daBBcd", "ABC"));
    }

    @Test
    public void test4() {
      assertTrue(canAbbr("Pi", "P"));
    }

    @Test
    public void test5() {
      assertFalse(canAbbr("AfPZN", "APZNC"));
    }

    @Test
    public void test6() {
      assertFalse(canAbbr("LDJAN", "LJJM"));
    }

    @Test
    public void test7() {
      assertTrue(canAbbr("UMKFW", "UMKFW"));
    }

    @Test
    public void test8() {
      assertFalse(canAbbr("KXzQ", "K"));
    }

    @Test
    public void test9() {
      assertTrue(canAbbr("LIT", "LIT"));
    }

    @Test
    public void test10() {
      assertTrue(canAbbr("QYCH", "QYCH"));
    }

    @Test
    public void test11() {
      assertTrue(canAbbr("DFIQG", "DFIQG"));
    }

    @Test
    public void test12() {
      assertFalse(canAbbr("sYOCa", "YOCN"));
    }

    @Test
    public void test13() {
      assertFalse(canAbbr("JHMWY", "HUVPW"));
    }
  }
}
