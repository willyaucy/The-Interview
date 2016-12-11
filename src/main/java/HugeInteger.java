import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 12/4/16.
 */
public class HugeInteger {
  private final String num;

  public HugeInteger(String num) {
    this.num = num;
  }

  public HugeInteger(int num) {
    this(String.valueOf(num));
  }

  public HugeInteger add(String anotherNum) {
    StringBuilder newNum = new StringBuilder();
    int carry = 0;

    for (int i = Math.max(num.length(), anotherNum.length()) - 1; i >= 0; i--) {
      int a = i < num.length() ? num.charAt(i) : 0;
      int b = i < anotherNum.length() ? num.charAt(i) : 0;
      int sum = a + b + carry;

      carry = sum / 10;
      newNum.append(Character.forDigit(sum % 10, 10));
    }

    while (newNum.length() != 0 && newNum.charAt(newNum.length() - 1) == '0') {
      newNum.deleteCharAt(newNum.length() - 1);
    }

    return new HugeInteger(newNum.reverse().toString());
  }

  public HugeInteger add(int anotherNum) {
    return add(String.valueOf(anotherNum));
  }

  public HugeInteger add(HugeInteger anotherNum) {
    return add(anotherNum.num);
  }

  @Override
  public String toString() {
    return num;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof HugeInteger && num.equals(((HugeInteger) o).num);
  }

  @Override
  public int hashCode() {
    return num.hashCode();
  }

  public static class HugeIntegerTest {
    @Test
    public void test0() {
      assertEquals(new HugeInteger(5), new HugeInteger(2).add(3));
    }
  }
}
