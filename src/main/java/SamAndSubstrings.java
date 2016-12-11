import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 12/3/16.
 * https://www.hackerrank.com/challenges/sam-and-substrings
 */
public class SamAndSubstrings {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(numCandies(scanner.next()));
  }

  public static int numCandies(String digits) {
    int output = 0;

    for (int i = 0; i < digits.length(); i++) {
      for (int j = i + 1; j <= digits.length(); j++) {
        output += new BigInteger(digits.substring(i, j))
            .mod(BigInteger.valueOf(1000000007))
            .intValueExact();

        output = output % 1000000007;
      }
    }

    return output;
  }

  public static class SamAndSubstringsTest {
    @Test
    public void test0() {
      assertEquals(23, numCandies("16"));
    }

    @Test
    public void test1() {
      assertEquals(164, numCandies("123"));
    }

    @Test
    public void test2() {
      assertEquals(1, numCandies("1"));
    }

    @Test
    public void test3() {
      assertEquals(0, numCandies("0"));
    }
  }
}
