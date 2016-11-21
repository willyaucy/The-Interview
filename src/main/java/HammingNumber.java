import org.junit.Test;

import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HammingNumber {
  private static final LazyIntStream HAMMING_STREAM = new LazyIntStream(
      1,
      () -> HammingNumber.mergeOrderedStream(
          generateHammingNumberStream().multiply(2),
          generateHammingNumberStream().multiply(3),
          generateHammingNumberStream().multiply(5)),
      1);

  public static LazyIntStream generateHammingNumberStream() {
    return HAMMING_STREAM;
  }

  public static LazyIntStream mergeOrderedStream(
      LazyIntStream stream1, LazyIntStream stream2, LazyIntStream stream3) {

    return mergeOrderedStream(stream1, mergeOrderedStream(stream2, stream3));
  }

  public static LazyIntStream mergeOrderedStream(LazyIntStream stream1, LazyIntStream stream2) {
    if (stream1.head < stream2.head) {
      return new LazyIntStream(
          stream1.head,
          () -> mergeOrderedStream(stream1.getRest(), stream2),
          1);
    } else if (stream1.head > stream2.head) {
      return new LazyIntStream(
          stream2.head,
          () -> mergeOrderedStream(stream2.getRest(), stream1),
          1);
    } else {
      return new LazyIntStream(
          stream1.head,
          () -> mergeOrderedStream(stream1.getRest(), stream2.getRest()),
          1);
    }
  }

  public static class LazyIntStream {
    private final int head;
    private final Supplier<LazyIntStream> rest;
    private final int restMultiplier;
    private LazyIntStream computedRest;

    public LazyIntStream(int head, Supplier<LazyIntStream> rest, int restMultiplier) {
      this.head = head;
      this.rest = rest;
      this.restMultiplier = restMultiplier;
      computedRest = null;
    }

    public int getHead() {
      return head;
    }

    public LazyIntStream getRest() {
      if (computedRest == null) {
        computedRest = rest.get().multiply(restMultiplier);
      }

      return computedRest;
    }
    
    public LazyIntStream multiply(int multiplier) {
      return new LazyIntStream(head * multiplier, rest, restMultiplier * multiplier);
    }
  }

  public static class HammingNumberTest {
    @Test
    public void test0() {
      System.out.println("Hello World");
      assertArrayEquals(
          new int[] {1, 2, 3, 4, 5, 6, 8, 9, 10, 12},
          Stream.iterate(generateHammingNumberStream(), s -> s.rest.get())
              .map(s -> s.head)
              .limit(10)
              .mapToInt(Integer::intValue)
              .toArray());
    }

    @Test
    public void test1() {
      System.out.println("Hello World");
      assertArrayEquals(
          new int[] {
              1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27,
              30, 32, 36, 40, 45, 48, 50, 54, 60, 64, 72, 75, 80, 81, 90,
              96, 100, 108, 120, 125, 128, 135, 144, 150, 160, 162, 180,
              192, 200, 216, 225, 240, 243, 250, 256, 270, 288, 300, 320,
              324, 360, 375, 384, 400, 405},
          Stream.iterate(generateHammingNumberStream(), s -> s.rest.get())
              .map(s -> s.head)
              .limit(62)
              .mapToInt(Integer::intValue)
              .toArray());
    }

    @Test
    public void test2() {
      System.out.println("Hello World");
      assertEquals(
          1000,
          Stream.iterate(generateHammingNumberStream(), s -> s.rest.get())
              .map(s -> s.head)
              .limit(1000)
              .distinct()
              .count());
    }
  }
}
