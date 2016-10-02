package srm698;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 10/1/16.
 */
public class BuffingPixie {
  public static int fastestVictory(int HP, int normalAttack, int buffedAttack) {
    if (HP == 0) {
      return 0;
    }

    int numTurns = 1;

    int hpWithAttack = HP - normalAttack;
    int hpWithFocus = HP;

    while (hpWithAttack > 0 && hpWithFocus > 0) {
      int newHpWithAttack = Math.min(hpWithFocus - buffedAttack, hpWithAttack - normalAttack);
      int newHpWithFocus = hpWithAttack;

      hpWithAttack = newHpWithAttack;
      hpWithFocus = newHpWithFocus;

      numTurns++;
    }

    return numTurns;
  }

  public static class BuffingPixieTest {

    @Test
    public void test1() {
      assertEquals(4, fastestVictory(5, 1, 3));
    }

    @Test
    public void test2() {
      assertEquals(1, fastestVictory(10, 20, 50));
    }

    @Test
    public void test3() {
      assertEquals(10, fastestVictory(24, 2, 5));
    }

    @Test
    public void test4() {
      assertEquals(13, fastestVictory(497, 40, 45));
    }

    @Test
    public void test5() {
      assertEquals(3360, fastestVictory(8400, 1, 5));
    }

    @Test
    public void test6() {
      assertEquals(5, fastestVictory(10, 2, 1));
    }
  }
}
