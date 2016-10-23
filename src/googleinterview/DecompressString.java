package googleinterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a compressed string, return a decompressed string.
 * eg: abc4[def] => abcdefdefdefdef
 *
 * Started: Oct 23 2:19PM
 * Finished: Oct 23 2:48PM
 *
 * Approach:
 * Loop through the string,
 * For alphabetic characters, append to output, increment loop index by 1.
 * For numeric character, find end index of the multiplier, store the multiplier as integer, move loop index to end of number.
 * For open parenthesis, find end index of closing parenthesis, decompress enclosed content and append to output, move loop index.
 */
public class DecompressString {
  public static String decompress(String s) {
    StringBuilder output = new StringBuilder();
    int multiplier = -1;

    int i = 0;

    while (i < s.length()) {
      if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
        output.append(s.charAt(i));
        i++;
      } else if (isNumeric(s.charAt(i))) {
        int endIndex = findNumberEndIndex(s, i);
        multiplier = Integer.parseInt(s.substring(i, endIndex));
        i = endIndex;
      } else if (s.charAt(i) == '[') {
        int endIndex = findClosingParenthesis(s, i);
        String decompressedSubstr = decompress(s.substring(i + 1, endIndex));

        output.append(multiplyString(decompressedSubstr, multiplier));
        i = endIndex + 1;
      } else {
        throw new IllegalStateException("Internal Error!");
      }
    }

    return output.toString();
  }

  private static String multiplyString(String s, int multiplier) {
    if (multiplier < 0) {
      throw new IllegalArgumentException();
    }

    StringBuilder output = new StringBuilder();

    for (int i = 0; i < multiplier; i++) {
      output.append(s);
    }

    return output.toString();
  }

  private static boolean isNumeric(char c) {
    return c >= '0' && c <= '9';
  }

  private static int findNumberEndIndex(String s, int startIndex) {
    for (int i = startIndex; i <= s.length(); i++) {
      if (i == s.length() || !isNumeric(s.charAt(i))) {
        return i;
      }
    }

    return -1;
  }

  private static int findClosingParenthesis(String s, int startIndex) {
    int openedParenthesis = 1;

    for (int i = startIndex + 1; i < s.length(); i++) {
      if (s.charAt(i) == '[') {
        openedParenthesis++;
      } else if (s.charAt(i) == ']') {
        openedParenthesis--;

        if (openedParenthesis == 0) {
          return i;
        }
      }
    }

    return -1;
  }

  public static class DecompressStringTest {
    @Test
    public void test1() {
      assertEquals("abcdefdefdefdef", decompress("abc4[def]"));
    }

    @Test
    public void test2() {
      assertEquals("abceeeeeeee", decompress("abc4[2[e]]"));
    }
  }
}
