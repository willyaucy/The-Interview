package guessnumber;

import org.junit.Test;

import java.lang.IllegalStateException;

import static org.junit.Assert.assertEquals;

public class GuesserSolution extends Guesser {

  public GuesserSolution(int number) {
    super(number);
  }

  public int getNumber() {
    int low = 1, high = 1000;

    while (low <= high) {
      int midPoint = low + (high - low) / 2;

      switch(guess(midPoint)) {
        case "Too high!":
          high = midPoint - 1;
          continue;
        case "Too low!":
          low = midPoint + 1;
          continue;
        case "Correct!":
          return midPoint;
        default:
          throw new IllegalStateException();
      }
    }

    throw new IllegalStateException();
  }

  public static class GuesserSolutionTest {
    @Test
    public void test0() {
      assertEquals(5, new GuesserSolution(5).getNumber());
    }

    @Test
    public void test1() {
      assertEquals(1, new GuesserSolution(1).getNumber());
    }

    @Test
    public void test2() {
      assertEquals(1000, new GuesserSolution(1000).getNumber());
    }

    @Test
    public void test3() {
      assertEquals(500, new GuesserSolution(500).getNumber());
    }

    @Test
    public void test4() {
      assertEquals(503, new GuesserSolution(503).getNumber());
    }
  }
}