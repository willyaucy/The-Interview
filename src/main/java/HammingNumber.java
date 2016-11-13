import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yauyin on 11/12/16.
 * Started: 3:19PM
 */
public class HammingNumber {

  /**
   * Generate the nth hamming number
   * @param n a nonnegative integer
   * @return the nth hamming number
   */
  public static int generateHammingNumber(int n) {

  }

  public static class HammingNumberStream {
    private int head;
    private Function<HammingNumberStream, HammingNumberStream> rest;

    private HammingNumberStream(
        int head,
        Supplier<HammingNumberStream> rest) {
      this.head = head;
      this.rest = rest;
    }

    public HammingNumberStream generate() {
      return new HammingNumberStream(
          1, stream -> merge(stream.multiply(2), stream.multiply(3), stream.multiply(5)));
    }

    public int next() {
      final int output = head;

      HammingNumberStream s = rest.apply(this);

      return head;
    }

    public HammingNumberStream multiply(int multiplier) {
      return new HammingNumberStream(head * multiplier, rest.apply());
    }

    private HammingNumberStream merge(HammingNumberStream stream1, HammingNumberStream stream2, HammingNumberStream stream3) {
      return null;
    }
  }

  public static class HammingNumberIterator {
    public final int head;
    public final HammingNumberIterator rest;
    public final int restMultiplier;

    private HammingNumberIterator(int base, HammingNumberIterator rest, int restMultiplier) {
      this.head = base;
      this.rest = rest;
      this.restMultiplier = restMultiplier;
    }

    public HammingNumberIterator multiply(int multiplier) {
      return new HammingNumberIterator(this.head * multiplier, multiplier);
    }

    private HammingNumberIterator next() {
      return new HammingNumberIterator(head * 2, rest, restMultiplier * 2)
          .merge(new HammingNumberIterator(head * 2, rest, restMultiplier * 3))
          .merge(new HammingNumberIterator(head * 2, rest, restMultiplier * 5));
    }

    private HammingNumberIterator merge(HammingNumberIterator otherIterator) {
      return new HammingNumberIterator();
    }

    public static HammingNumberIterator generate() {
      return new HammingNumberIterator(
          1,
          ,
          1);
    }
  }
}
