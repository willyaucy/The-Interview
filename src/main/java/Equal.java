import org.junit.Test;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/equal
 * Created by yau on 12/11/16.
 */
public class Equal {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numInputs = scanner.nextInt();

    for (int i = 0; i < numInputs; i++) {
      scanner.nextInt(); // number of coworkers. ignored
      int[] numChocolates = Stream.of(scanner.next().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }
  }

  public static int minOperations(int[] numChocolates) {
    return -1;
  }

  public static class EqualTest {
    @Test
    public void test0() {
      assertEquals(2, minOperations(new int[] { 2, 2, 3, 7 }));
    }
  }
}
