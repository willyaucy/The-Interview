package gperm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 8/13/16.
 */
public class GpermTest {
//  @Test
//  public void generatePermTest() {
//    System.out.println(new Gperm().generatePerms(5));
//  }

  @Test
  public void test1() {
    assertEquals(
        1,
        new Gperm().countfee(
            new int[]{0},
            new int[]{1}));
  }

  @Test
  public void test2() {
    assertEquals(
        2,
        new Gperm().countfee(
            new int[]{0,1},
            new int[]{1,2}));
  }

  @Test
  public void test3() {
    assertEquals(
        3,
        new Gperm().countfee(
            new int[]{4,7,7},
            new int[]{7,4,4}));
  }

  @Test
  public void test4() {
    assertEquals(
        4,
        new Gperm().countfee(
            new int[]{0,0,1},
            new int[]{1,2,2}));
  }

  @Test
  public void test6() {
    assertEquals(
        6,
        new Gperm().countfee(
            new int[]{7,8,9},
            new int[]{4,5,6}));
  }

  @Test
  public void test53() {
    assertEquals(
        53,
        new Gperm().countfee(
            new int[]{45,28,42,5,27,27,42,36,14,27,19,42,24,27,8,31,24,27,14,28},
            new int[]{45,27,45,8,34,34,28,0,11,42,24,19,14,31,45,42,14,24,28,27}));
  }
}
