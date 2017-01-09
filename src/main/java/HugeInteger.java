import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 12/4/16.
 */
public class HugeInteger {
  private final String val;

  public HugeInteger(String val) {
    this.val = val;
  }

  public HugeInteger(int val) {
    this.val = String.valueOf(val);
  }

  public HugeInteger add(HugeInteger anotherNum) {
    StringBuilder newVal = new StringBuilder();
    int carry = 0;

    for (int i = 0; i < Math.max(length(), anotherNum.length()); i++) {
      int sumDigit = getDigit(i) + anotherNum.getDigit(i) + carry;

      newVal.append(sumDigit % 10);
      carry = sumDigit / 10;
    }

    if (carry > 0) {
      newVal.append(carry);
    }

    return new HugeInteger(newVal.toString());
  }

  public HugeInteger multiply(HugeInteger anotherNum) {
    return IntStream.range(0, anotherNum.length())
        .mapToObj(i -> multiplyDigit(anotherNum.getDigit(i)).prependZeros(i))
        .reduce(HugeInteger::add).get();
  }

  private HugeInteger multiplyDigit(int digit) {
    StringBuilder newVal = new StringBuilder();
    int carry = 0;

    for (int i = 0; i < length(); i++) {
      int productDigit = getDigit(i) * digit + carry;

      newVal.append(productDigit % 10);
      carry = productDigit / 10;
    }

    if (carry > 0) {
      newVal.append(carry);
    }

    return new HugeInteger(newVal.toString());
  }

  private HugeInteger prependZeros(int numZeros) {
    if (numZeros == 0) {
      return this;
    }

    StringBuilder newVal = new StringBuilder();

    for (int i = 0; i < numZeros; i++) {
      newVal.append('0');
    }

    newVal.append(val);

    return new HugeInteger(newVal.toString());
  }

  public int getDigit(int i) {
    return i >= val.length() ? 0 : Character.digit(val.charAt(i), 10);
  }

  public int length() {
    return val.length();
  }

  public int toInt() {
    return Integer.parseInt(val);
  }

  @Override
  public String toString() {
    return val;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof HugeInteger && val.equals(((HugeInteger) o).val);
  }

  @Override
  public int hashCode() {
    return val.hashCode();
  }

  public static class HugeIntegerTest {
    @Test
    public void test0() {
      assertEquals(5, new HugeInteger(2).add(new HugeInteger(3)).toInt());
    }

    @Test
    public void test1() {
      assertEquals(6, new HugeInteger(2).multiply(new HugeInteger(3)).toInt());
    }

    @Test
    public void test2() {
      assertEquals(0, new HugeInteger(99).multiply(new HugeInteger(0)).toInt());
    }

    @Test
    public void test3() {
      assertEquals(144, new HugeInteger(12).multiply(new HugeInteger(12)).toInt());
    }

    @Test
    public void test4() {
      assertEquals(5535, new HugeInteger(123).multiply(new HugeInteger(45)).toInt());
    }

    @Test
    public void test5() {
      assertEquals(5535, new HugeInteger(45).multiply(new HugeInteger(123)).toInt());
    }
  }
}
