import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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
  public static LazyStream<Integer> generateHammingNumberStream() {
    return new LazyStream<>(
        1,
        () -> HammingNumber.<Integer>mergeOrderedStream(
            generateHammingNumberStream().map(n -> n * 2),
            generateHammingNumberStream().map(n -> n * 3),
            generateHammingNumberStream().map(n -> n * 5)));
  }

  public static <T extends Comparable<T>> LazyStream<T> mergeOrderedStream(
      LazyStream<T> stream1, LazyStream<T> stream2, LazyStream<T> stream3) {

    List<LazyStream<T>> streams = Arrays.asList(stream1, stream2, stream3);
    streams.sort((s1, s2) -> s1.head.compareTo(s2.head));

    if (streams.get(1).head.equals(streams.get(0).head)) {
      streams.set(1, streams.get(1).rest.get());
    }

    if (streams.get(2).head.equals(streams.get(0).head)) {
      streams.set(2, streams.get(2).rest.get());
    }

    return new LazyStream<>(
        streams.get(0).head,
        () -> mergeOrderedStream(
            streams.get(0).rest.get(),
            streams.get(1),
            streams.get(2)));
  }

  public static class LazyStream<T> {
    public final T head;
    public final Supplier<LazyStream<T>> rest;

    public LazyStream(T head, Supplier<LazyStream<T>> rest) {
      this.head = head;
      this.rest = rest;
    }

    public <U> LazyStream<U> map(Function<T, U> fn) {
      return new LazyStream<>(fn.apply(head), () -> rest.get().map(fn));
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
