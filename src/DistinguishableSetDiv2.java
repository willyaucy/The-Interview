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
  public static int count(String[] answers) {
    if (answers.length == 0 || answers[0].length() == 0) {
      return 0;
    }

    final int numberOfQuestions = answers[0].length();
    int counter[][] = new int[numberOfQuestions][numberOfQuestions];

    for (int i = 0; i < numberOfQuestions; i++) {
      for (int k = 0; k <= i; k++) {
        if (i == k) {

//          counter[i][k] = Arrays.stream(answers).map(answer -> answer.charAt(i)).distinct();
        }
      }
    }
    return 0;
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
