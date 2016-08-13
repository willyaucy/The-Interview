package gperm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yauyin on 8/13/16.
 * Started: 3:37pm
 */
public class Gperm {
  public int countfee(int[] x, int[] y) {
    int numPaintedEdges = 0;

    List<List<Integer>> perms = generatePerms(50);

    return perms
        .stream()
        .mapToInt(perm -> calculateForPerm(x, y, perm))
        .reduce(Math::min)
        .orElse(0);
  }

  public int calculateForPerm(int[] x, int[] y, List<Integer> perm) {
    boolean[] isXPainted = new boolean[x.length];
    boolean[] isYPainted = new boolean[y.length];

    int numPaintedEdges = 0;
    int cost = 0;

    for (int vertex : perm) {
      for (int i = 0; i < x.length; i++) {
        if (x[i] == vertex && !isXPainted[i]) {
          isXPainted[i] = true;
          if (isYPainted[i]) {
            numPaintedEdges++;
          }
        }
      }

      for (int i = 0; i < y.length; i++) {
        if (y[i] == vertex && !isYPainted[i]) {
          isYPainted[i] = true;
          if (isXPainted[i]) {
            numPaintedEdges++;
          }
        }
      }

      cost += numPaintedEdges;
    }

    return cost;
  }

  /**
   * generate permutations of Integers from 0 (inclusive) to n (exclusive)
   * @param n
   * @return list of different permutations of Integers from 0 (inclusive) to n (exclusive)
   */
  public List<List<Integer>> generatePerms(int n) {
    if (n < 1) {
      return new ArrayList<>();
    } else if (n == 1) {
      return Arrays.asList(Arrays.asList(0));
    }

    List<List<Integer>> output = new ArrayList<>();

    for (List<Integer> permWithoutLast : generatePerms(n - 1)) {
      for (int i = 0; i <= permWithoutLast.size(); i++) {
        List<Integer> newPerm = new ArrayList<>();

        newPerm.addAll(permWithoutLast.subList(0, i));
        newPerm.add(n - 1);
        newPerm.addAll(permWithoutLast.subList(i, permWithoutLast.size()));

        output.add(newPerm);
      }
    }

    return output;
  }
}
