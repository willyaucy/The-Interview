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
 * Started: 1/4/17 3:15pm
 * Finished 1/4/17 3:34pm
 */
public class AbbreviationTakeThree {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numQueries = scanner.nextInt();

    Stream.generate(() -> {
      String a = scanner.next();
      String b = scanner.next();

      return Abbreviator.canAbbr(a, b);
    }).limit(numQueries).map(result -> result ? "YES" : "NO").forEach(System.out::println);
  }

  private static class StringPair {
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

  public static class Abbreviator {
    Map<StringPair, Boolean> memo;
    
    private Abbreviator() {
      this.memo = new HashMap<>();
    }
    
    public static boolean canAbbr(String a, String b) {
      return new Abbreviator().canAbbrInternal(a, b);
    }

    private boolean canAbbrInternal(String a, String b) {
      return memo.computeIfAbsent(new StringPair(a, b), abPair -> {
        if (a.length() == b.length()) {
          return a.toUpperCase().equals(b);
        } else if (b.length() == 0) {
          return a.toLowerCase().equals(a);
        } else if (a.length() < b.length()) {
          return false;
        }

        if (Character.isUpperCase(a.charAt(0))) {
          return a.charAt(0) == b.charAt(0) && canAbbrInternal(a.substring(1), b.substring(1));
        } else if (Character.toUpperCase(a.charAt(0)) == b.charAt(0)) {
          return canAbbrInternal(a.substring(1), b.substring(1))
              || canAbbrInternal(a.substring(1), b);
        } else {
          return canAbbrInternal(a.substring(1), b);
        }
      });
    }
  }

  public static class AbbreviationTest {
    @Test
    public void test0() {
      assertTrue(Abbreviator.canAbbr("daBcd", "ABC"));
    }

    @Test
    public void test1() {
      assertTrue(Abbreviator.canAbbr("daBbcd", "ABC"));
    }

    @Test
    public void test2() {
      assertTrue(Abbreviator.canAbbr("dabbbbbBbcd", "ABC"));
    }
    @Test
    public void test3() {
      assertFalse(Abbreviator.canAbbr("daBBcd", "ABC"));
    }

    @Test
    public void test4() {
      assertTrue(Abbreviator.canAbbr("Pi", "P"));
    }

    @Test
    public void test5() {
      assertFalse(Abbreviator.canAbbr("AfPZN", "APZNC"));
    }

    @Test
    public void test6() {
      assertFalse(Abbreviator.canAbbr("LDJAN", "LJJM"));
    }

    @Test
    public void test7() {
      assertTrue(Abbreviator.canAbbr("UMKFW", "UMKFW"));
    }

    @Test
    public void test8() {
      assertFalse(Abbreviator.canAbbr("KXzQ", "K"));
    }

    @Test
    public void test9() {
      assertTrue(Abbreviator.canAbbr("LIT", "LIT"));
    }

    @Test
    public void test10() {
      assertTrue(Abbreviator.canAbbr("QYCH", "QYCH"));
    }

    @Test
    public void test11() {
      assertTrue(Abbreviator.canAbbr("DFIQG", "DFIQG"));
    }

    @Test
    public void test12() {
      assertFalse(Abbreviator.canAbbr("sYOCa", "YOCN"));
    }

    @Test
    public void test13() {
      assertFalse(Abbreviator.canAbbr("JHMWY", "HUVPW"));
    }
  }
}
