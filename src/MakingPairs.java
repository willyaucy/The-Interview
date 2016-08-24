import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 8/21/16.
 * Started: 08/21/2016 3:08pm
 * Finished: 08/21/2016 3:13pm
 */
public class MakingPairs {
  public static int get(int[] card) {
    int numberOfPairs = 0;

    for (int numberOfCards : card) {
      numberOfPairs += numberOfCards / 2;
    }

    return numberOfPairs;
  }

  public static class MakingPairsTest {
    @Test
    public void test1() {
      assertEquals(
          0,
          get(new int[] {1})
      );
    }

    @Test
    public void test2() {
      assertEquals(
          102,
          get(new int[] {43,23,10,39,39,22,22,0,3,4,3,2})
      );
    }
  }
}
