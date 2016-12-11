import org.junit.Test;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 12/3/16, 5:20pm
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public class FibonacciModified {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long t1 = scanner.nextLong();
    long t2 = scanner.nextLong();
    int n = scanner.nextInt();

    System.out.println(fibModified(t1, t2, n).toString());
  }

  public static class BigIntegerPair {
    public final BigInteger left;
    public final BigInteger right;

    public BigIntegerPair(BigInteger left, BigInteger right) {
      this.left = left;
      this.right = right;
    }
  }

  public static BigInteger fibModified(long t1, long t2, int n) {
    return Stream
        .iterate(
            new BigIntegerPair(BigInteger.valueOf(t1), BigInteger.valueOf(t2)),
            pair -> new BigIntegerPair(pair.right, pair.left.add(pair.right.multiply(pair.right))))
        .skip(n - 1)
        .findFirst()
        .get()
        .left;
  }

  public static class FibonacciModifiedTest {
    @Test
    public void test0() {
      assertEquals(BigInteger.valueOf(5), fibModified(0, 1, 5));
    }

    @Test
    public void test1() {
      assertEquals(BigInteger.valueOf(0), fibModified(0, 1, 1));
      assertEquals(BigInteger.valueOf(1), fibModified(0, 1, 2));
      assertEquals(BigInteger.valueOf(1), fibModified(0, 1, 3));
      assertEquals(BigInteger.valueOf(2), fibModified(0, 1, 4));
      assertEquals(BigInteger.valueOf(5), fibModified(0, 1, 5));
      assertEquals(BigInteger.valueOf(27), fibModified(0, 1, 6));
    }
  }
}
