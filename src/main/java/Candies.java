import org.junit.Test;

import java.util.Scanner;
import java.util.stream.LongStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/candies
 * Started 12/03/16 3:27pm
 */
public class Candies {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    final long arrayLength = scanner.nextLong();
    final long[] ratings = LongStream.generate(() -> scanner.nextLong()).limit(arrayLength).toArray();

    System.out.println(LongStream.of(minimizeCandies(ratings)).sum());
  }

  public static long[] minimizeCandies(long[] ratings) {
    if (ratings.length == 0) {
      return new long[0];
    }

    long[] numCandies = new long[ratings.length];
    numCandies[0] = 1L;

    for (int i = 1; i < ratings.length; i++) {
      if (ratings[i] > ratings[i - 1]) {
        numCandies[i] = numCandies[i - 1] + 1L;
      } else {
        numCandies[i] = 1L;
      }
    }


    for (int i = ratings.length - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1] && numCandies[i] <= numCandies[i + 1]) {
        numCandies[i] = numCandies[i + 1] + 1L;
      }
    }

    return numCandies;
  }

  public static class CandiesTest {
    @Test
    public void test0() {
      assertArrayEquals(
          new long[]{1, 2, 1},
          minimizeCandies(new long[]{1, 2, 2}));
    }

    @Test
    public void test1() {
      assertArrayEquals(
          new long[]{1, 5, 4, 3, 2, 1},
          minimizeCandies(new long[]{3, 5, 4, 3, 2, 1}));
    }

    @Test
    public void test2() {
      assertEquals(
          19,
          LongStream.of(minimizeCandies(new long[] {10, 2, 4, 2, 6, 1, 7, 8, 9, 2, 1})).sum());
    }
  }
}
