package googleinterview.debounce;

import org.junit.Test;

/**
 * A Processor takes in a DataPoint and processes it.
 * A DataPoint consists of a url and a payload.
 *
 * However, to make sure our server does not get overloaded, we would like
 * to debounce the calls.
 *
 * Concretely, after we processed a DataPoint from a url,
 * we do not want to process any subseqent DataPoints with the same url
 * until a timeout period is passed.
 *
 * eg:
 * timeout - 2
 * time     -  | 0 | 1 | 2 | 3 | 4 | 5 |
 * url      -  | a | a | b | a | b | b |
 * process? -  | y | n | y | y | n | y |
 */
public class DebouncedProcessor implements Processor {
  private final Processor processor;
  private final Clock clock;
  private final long timeout;

  public DebouncedProcessor(Processor processor, Clock clock, long timeout) {
    this.processor = processor;
    this.clock = clock;
    this.timeout = timeout;
  }

  @Override
  public void process(DataPoint dataPoint) {

  }

  public class DebouncedProcessorTest {
    @Test
    public void test1() {
    }
  }
}
