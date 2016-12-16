import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yau on 12/11/16.
 */
public class PermutationStream {
  public static Stream<List<Integer>> generatePermutations(List<Integer> numbers) {
    if (numbers.isEmpty()) {
      return Stream.empty();
    } else if (numbers.size() == 1) {
      return Stream.of(numbers);
    }

    return generatePermutations(numbers.subList(1, numbers.size()))
        .flatMap(perm ->
            IntStream.rangeClosed(0, perm.size())
                .mapToObj(idx -> insert(perm, idx, numbers.get(0))));
  }

  private static List<Integer> insert(List<Integer> list, int index, int value) {
    List<Integer> outputList = new ArrayList<>(list.size() + 1);

    outputList.addAll(list.subList(0, index));
    outputList.add(value);
    outputList.addAll(list.subList(index, list.size()));

    return outputList;
  }

  public static class PermutationStreamTest {
    @Test
    public void test0() {
      Stream<List<Integer>> s = generatePermutations(
          IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList()));

      Object[] o = s.limit(10).toArray();
    }
  }
}
