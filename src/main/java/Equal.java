import java.io.Reader;
import java.util.Scanner;
import java.util.stream.Stream;

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
}
