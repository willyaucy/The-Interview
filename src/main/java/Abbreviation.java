import org.junit.Test;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

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
    if (a.length() == b.length()) {
      return a.toUpperCase().equals(b);
    } else if (a.length() < b.length()) {
      return false;
    }

    if (Character.toUpperCase(a.charAt(0)) == b.charAt(0)) {
      if (Character.isUpperCase(a.charAt(0))) {
        return canAbbr(a.substring(1), b.substring(1));
      } else {
        return canAbbr(a.substring(1), b.substring(1)) || canAbbr(a.substring(1), b);
      }
    }

    if (Character.toUpperCase(a.charAt(a.length() - 1)) == b.charAt(b.length() - 1)) {
      if (Character.isUpperCase(a.charAt(a.length() - 1))) {
        return canAbbr(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1));
      } else {
        return canAbbr(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1))
            || canAbbr(a.substring(0, a.length() - 1), b);
      }
    }

    return canAbbr(a.substring(1, a.length() - 1), b);
  }

  public static class AbbreviationTest {
    @Test
    public void test0() {
      assertTrue(canAbbr("daBcd", "ABC"));
    }
  }
}
