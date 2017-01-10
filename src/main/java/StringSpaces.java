import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 1/10/17.
 */
public class StringSpaces {
  /**
   * Remove excessive spaces from a string.
   * eg: "The sky  is blue " --> "The sky is blue"
   * @param s the input string
   * @return the string without excessive spaces
   */
  public static String removeExcessiveSpaces(String s) {
    StringBuilder sb = new StringBuilder();

    int firstNonSpaceIdx = IntStream.range(0, s.length())
        .filter(idx ->  s.charAt(idx) != ' ').findFirst().getAsInt();

    Character lastChar = null;

    for (char c : s.substring(firstNonSpaceIdx).toCharArray()) {
      if (c != ' ') {
        if (lastChar != null && lastChar == ' ') {
          sb.append(' ');
        }

        sb.append(c);
      }

      lastChar = c;
    }

    return sb.toString();
  }

  public static class StringSpacesTest {
    @Test
    public void test0() {
      assertEquals("The sky is blue", removeExcessiveSpaces(" The sky  is blue "));
    }
  }
}
