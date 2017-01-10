import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by yau on 12/4/16.
 */
public class HugeInteger implements Comparable<HugeInteger> {
  private final String val;

  private HugeInteger(String val) {
    this.val = val;
  }

  public static HugeInteger of(String val) {
    return new HugeInteger(new StringBuilder(val).reverse().toString());
  }

  public static HugeInteger of(int val) {
    return of(String.valueOf(val));
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
        .mapToObj(i -> multiplyDigit(anotherNum.getDigit(i)).appendZeros(i))
        .reduce(HugeInteger::add).get();
  }

  private HugeInteger multiplyDigit(int digit) {
    if (digit == 0) {
      return new HugeInteger("0");
    }

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

  private HugeInteger appendZeros(int numZeros) {
    if (val.equals("0") || numZeros == 0) {
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
    return Integer.parseInt(new StringBuilder(val).reverse().toString());
  }

  @Override
  public String toString() {
    return String.format("%s", new StringBuilder(val).reverse().toString());
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof HugeInteger && val.equals(((HugeInteger) o).val);
  }

  @Override
  public int hashCode() {
    return val.hashCode();
  }

  @Override
  public int compareTo(HugeInteger other) {
    if (length() > other.length()) {
      return 1;
    } else if (length() < other.length()) {
      return -1;
    } else {
      for (int i = 0; i < length(); i++) {
        if (getDigit(i) > other.getDigit(i)) {
          return 1;
        } else if (getDigit(i) < other.getDigit(i)) {
          return -1;
        }
      }

      return 0;
    }
  }

  public static class HugeIntegerTest {
    @Test
    public void test0() {
      assertEquals(5, HugeInteger.of(2).add(HugeInteger.of(3)).toInt());
    }

    @Test
    public void test1() {
      assertEquals(6, HugeInteger.of(2).multiply(HugeInteger.of(3)).toInt());
    }

    @Test
    public void test2() {
      assertEquals(0, HugeInteger.of(99).multiply(HugeInteger.of(0)).toInt());
    }

    @Test
    public void test3() {
      assertEquals(144, HugeInteger.of(12).multiply(HugeInteger.of(12)).toInt());
    }

    @Test
    public void test4() {
      assertEquals(5535, HugeInteger.of(123).multiply(HugeInteger.of(45)).toInt());
    }

    @Test
    public void test5() {
      assertEquals(5535, HugeInteger.of(45).multiply(HugeInteger.of(123)).toInt());
    }
  }
}
