import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/21/16.
 * Started: 3:18pm
 */
public class DistinguishableSetDiv2 {
  public static int count2(String[] answers) {
    if (answers.length == 0) {
      return 0;
    }

    final int numberOfQuestions = answers[0].length();

    if (numberOfQuestions == 0) {
      return 0;
    }

    if (Arrays.stream(answers).distinct().count() != answers.length) {
      return 0;
    }

    String[] answersWithoutFirst = Arrays
        .stream(answers)
        .map(answer -> answer.substring(0, numberOfQuestions - 1))
        .toArray(String[]::new);

    String[] answersWithoutLast = Arrays
        .stream(answers)
        .map(answer -> answer.substring(1))
        .toArray(String[]::new);

    String[] answersWithoutFirstLast = Arrays
        .stream(answers)
        .map(answer -> answer.substring(1, Math.max(1, numberOfQuestions - 1)))
        .toArray(String[]::new);

    return 1 + count(answersWithoutFirst) + count(answersWithoutLast) - count(answersWithoutFirstLast);
  }

  public static int count(String[] answers) {
    if (answers.length == 0) {
      return 0;
    }

    final int numberOfQuestions = answers[0].length();

    if (numberOfQuestions == 0) {
      return 0;
    }

    int counter[][] = new int[numberOfQuestions][numberOfQuestions];

    for (int problemSize = 1; problemSize <= numberOfQuestions; problemSize++) {
      for (int i = 0; i <= numberOfQuestions - problemSize; i++) {
        final int rangeFrom = i;
        final int rangeTo = i + problemSize - 1;

        boolean isRangeDistinguishable = Arrays.stream(answers)
            .map(answer -> answer.substring(rangeFrom, rangeTo + 1))
            .distinct()
            .count() == answers.length;

        counter[rangeFrom][rangeTo] = isRangeDistinguishable ? 1 : 0;

        switch (problemSize) {
          case 1:
            break;
          case 2:
            counter[rangeFrom][rangeTo] += counter[rangeFrom][rangeFrom] + counter[rangeTo][rangeTo];
            break;
          default:
            counter[rangeFrom][rangeTo] +=
                counter[rangeFrom + 1][rangeTo] +
                counter[rangeFrom][rangeTo - 1] -
                counter[rangeFrom + 1][rangeTo - 1];
            break;
        }
      }
    }

    printMatrix(counter);

    return counter[0][numberOfQuestions - 1];
  }

  public static void printMatrix(int[][] matrix) {

    for (int[] row : matrix) {
      for (int cell : row) {
        System.out.printf("%3d  ", cell);
      }
      System.out.println();
    }
  }

  public static class DistinguishableSetDiv2Test {
    @Test
    public void test1() {
      assertEquals(
          2,
          count(new String[] {"AA","AB","CC"})
      );
    }

    @Test
    public void test2() {
      assertEquals(
          0,
          count(new String[] {"XYZ","XYZ","XYZ"})
      );
    }

    @Test
    public void test3() {
      assertEquals(
          11,
          count(new String[] {"AAAA","BACA","CDCE"})
      );
    }

    @Test
    public void test4() {
      assertEquals(
          1017,
          count(new String[] {"HGHHGUHUHI","BQHJWOSZMM","NDKSKCNXND","QOEOEIWIDS","IIQIWUNNZM"})
      );
    }

    @Test
    public void test5() {
      assertEquals(
          5,
          count(new String[] {"XYZ","XAB","YAC"})
      );
    }
  }
}
