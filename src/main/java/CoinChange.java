import org.junit.Test;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/coin-change
 * Created by yau on 12/11/16. 3:14am
 */
public class CoinChange {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int amount = Integer.parseInt(scanner.next().split(" ")[0]);
    int[] coins = Stream.of(scanner.next().split(" "))
        .mapToInt(Integer::parseInt).sorted().toArray();

    System.out.println(numWays(amount, coins));
  }

  public static int numWays(int amount, int[] coins) {
    return -1;
  }

  public static class CoinChangeTest {
    @Test
    public void test0() {
      assertEquals(4, numWays(4, new int[] {1, 2, 3}));
    }

    @Test
    public void test1() {
      assertEquals(5, numWays(10, new int[] {2, 3, 5, 6}));
    }
  }
}
