package hackerrankcity;

import org.junit.Test;

import java.util.Scanner;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/hr-city
 */
public class HackerRankCity {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numSteps = scanner.nextInt();
    int[] edgeLengths = IntStream.generate(scanner::nextInt).limit(numSteps).toArray();

    System.out.println(calculateDistances(edgeLengths));
  }

  static int calculateDistances(int[] edgeLengths) {
    return -1;
  }

  public static class HackerRankCityTest {
    @Test
    public void test0() {
      assertEquals(29, calculateDistances(new int[] {1}));
    }

    @Test
    public void test1() {
      assertEquals(2641, calculateDistances(new int[] {2, 1}));
    }
  }
}
