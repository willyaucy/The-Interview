import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yauyin on 11/12/16.
 * Started: 3:19PM
 */
public class HammingNumber {
  private static final LazyStream<Integer> HAMMING_NUMBER_STREAM = new LazyStream<>(
      1,
      () -> HammingNumber.<Integer>mergeOrderedStream(
          generateHammingNumberStream().map(n -> n * 2),
          generateHammingNumberStream().map(n -> n * 3),
          generateHammingNumberStream().map(n -> n * 5)));

  public static LazyStream<Integer> generateHammingNumberStream() {
    return HAMMING_NUMBER_STREAM;
  }

  public static <T extends Comparable<T>> LazyStream<T> mergeOrderedStream(
      LazyStream<T> stream1, LazyStream<T> stream2, LazyStream<T> stream3) {

    return mergeOrderedStream(stream1, mergeOrderedStream(stream2, stream3));
  }

  public static <T extends Comparable<T>> LazyStream<T> mergeOrderedStream(
      LazyStream<T> stream1, LazyStream<T> stream2) {

    if (stream1.getHead().compareTo(stream2.getHead()) < 0) {
      return new LazyStream<T>(
          stream1.getHead(),
          () -> mergeOrderedStream(stream1.getRest(), stream2));
    } else if (stream1.getHead().compareTo(stream2.getHead()) > 0) {
      return new LazyStream<T>(
          stream2.getHead(),
          () -> mergeOrderedStream(stream2.getRest(), stream1));
    } else {
      return new LazyStream<T>(
          stream1.getHead(),
          () -> mergeOrderedStream(stream1.getRest(), stream2.getRest()));
    }
  }

  public static class LazyStream<T> {
    public final T head;
    public final Supplier<LazyStream<T>> rest;
    public LazyStream<T> computedRest;

    public LazyStream(T head, Supplier<LazyStream<T>> rest) {
      this.head = head;
      this.rest = rest;
    }
    
    public T getHead() {
      return head;
    }
    
    public LazyStream<T> getRest() {
      if (computedRest == null) {
        computedRest = rest.get();
      }

      return computedRest;
    }

    public <U> LazyStream<U> map(Function<T, U> fn) {
      return new LazyStream<>(fn.apply(head), () -> getRest().map(fn));
    }
  }

  public static class HammingNumberTest {
    @Test
    public void test0() {
      assertArrayEquals(
          new int[] {1, 2, 3, 4, 5, 6, 8, 9, 10, 12},
          Stream.iterate(generateHammingNumberStream(), s -> s.getRest())
              .mapToInt(s -> s.getHead())
              .limit(10)
              .toArray());
    }

    @Test
    public void test1() {
      assertArrayEquals(
          new int[] {
              1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27,
              30, 32, 36, 40, 45, 48, 50, 54, 60, 64, 72, 75, 80, 81, 90,
              96, 100, 108, 120, 125, 128, 135, 144, 150, 160, 162, 180,
              192, 200, 216, 225, 240, 243, 250, 256, 270, 288, 300, 320,
              324, 360, 375, 384, 400, 405},
          Stream.iterate(generateHammingNumberStream(), s -> s.getRest())
              .mapToInt(s -> s.getHead())
              .limit(62)
              .toArray());
    }

    @Test
    public void test2() {
      assertEquals(
          1000,
          Stream.iterate(generateHammingNumberStream(), s -> s.getRest())
              .mapToInt(s -> s.getHead())
              .limit(1000)
              .distinct()
              .count());
    }
  }
}
