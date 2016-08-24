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
    return (int)Math.pow(2, answers[0].length()) - countIndistinguishable(Arrays.asList(answers));
  }

  private static int countIndistinguishable(List<String> answers) {
    int number = 0;

    if (!answers.isEmpty()) {
      for (int i = 0; i < answers.get(0).length(); i++) {
        if (areAnswersEqual(answers, i)) {
          number += 1 + number;
        }
      }
    }

    return number + 1;
  }

  private static boolean areAnswersEqual(List<String> answers, int index) {
    return answers.stream()
        .map(answer -> answer.charAt(index))
        .distinct()
        .count() == 1;
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
