package ropestring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yauyin on 8/13/16.
 */
public class Ropestring {
  public static void main(String[] args) {
    System.out.println(new Ropestring().makerope("..."));
  }

  public String makerope(String s) {
    List<Integer> ropes = new ArrayList<>();

    int currentRopeLength = 0;

    for (int i = 0; i <= s.length(); i++) {
      if (i != s.length() && s.charAt(i) == '-') {
        currentRopeLength++;
      } else if (currentRopeLength != 0) {
        ropes.add(currentRopeLength);
        currentRopeLength = 0;
      }
    }

    ropes.sort((a, b) -> {
      if (a % 2 == 0 && b % 2 != 0) {
        return -1;
      } else if (b % 2 == 0 && a % 2 != 0) {
        return 1;
      } else {
        return b - a;
      }
    });

    String outputWithoutTrailingDots = ropes.stream()
        .map(length -> getDashString(length))
        .reduce((a, b) -> a + '.' + b)
        .orElse("");

    return outputWithoutTrailingDots + generateDotString(s.length() - outputWithoutTrailingDots.length());
  }

  public String getDashString(int length) {
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < length; i++) {
      output.append('-');
    }

    return output.toString();
  }

  private String generateDotString(int length) {
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < length; i++) {
      output.append('.');
    }

    return output.toString();
  }
}
