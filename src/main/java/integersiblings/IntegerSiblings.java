package integersiblings;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by yau on 2/3/17.
 */
public class IntegerSiblings {
  public static void main(String args[]) {
    System.out.println(solution(314159));
    System.out.println(solution(900));
    System.out.println(solution(0));
    System.out.println(solution(10_000));
    System.out.println(solution(9_999));
    System.out.println(solution(9_990));
    System.out.println(solution(1010));
  }

  public static int solution(int N) {
    char[] digits = String.valueOf(N).toCharArray();
    Arrays.sort(digits);

    return Integer.parseInt(new StringBuilder().append(digits).reverse().toString());
  }

  int solutionX(int[] A) {
    int n = A.length;
    int result = 0;
    for (int i = 0; i < n - 1; i++) {
      if (A[i] == A[i + 1])
        result = result + 1;
    }
    int r = 0;
    for (int i = 0; i < n; i++) {
      int count = 0;
      if (i > 0) {
        if (A[i - 1] != A[i])
          count = count + 1;
        else
          count = count - 1;
      }
      if (i < n - 1) {
        if (A[i + 1] != A[i])
          count = count + 1;
        else
          count = count - 1;
      }
      r = Math.max(r, count);
    }
    return result + r;
  }
}
