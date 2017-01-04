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
 * Started: 1/3/17 11:38pm
 */
public class AbbreviationTakeTwo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numQueries = scanner.nextInt();

    Stream.generate(() -> {
      String a = scanner.next();
      String b = scanner.next();

      return canAbbr(a, b);
    }).limit(numQueries).map(result -> result ? "YES" : "NO").forEach(System.out::println);
  }

  private static final class StringPair {
    final String a, b;

    StringPair(String a, String b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof StringPair
          && Objects.equals(((StringPair) o).a, a)
          && Objects.equals(((StringPair) o).b, b);
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b);
    }
  }

  static boolean canAbbr(String a, String b) {
    return new Abbreviator().canAbbr(a, b);
  }

  public static class Abbreviator {
    Map<StringPair, Boolean> memo = new HashMap<>();

    boolean canAbbr(String a, String b) {
      return memo.computeIfAbsent(new StringPair(a, b), abPair -> {
        if (a.length() == b.length()) {
          return a.toUpperCase().equals(b);
        } else if (a.length() < b.length()) {
          return false;
        } else if (b.length() == 0) {
          return a.equals(a.toLowerCase());
        }

        if (Character.isUpperCase(a.charAt(0))) {
          return a.charAt(0) == b.charAt(0) && canAbbr(a.substring(1), b.substring(1));
        } else {
          return Character.toUpperCase(a.charAt(0)) == b.charAt(0) && canAbbr(a.substring(1), b.substring(1))
              || canAbbr(a.substring(1), b);
        }
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
