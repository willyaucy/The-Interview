package googleinterview.throttle;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * A Processor takes in a data point and processes it.
 * A data point consists of a url and a payload.
 *
 * However, to make sure our server does not get overloaded, we would like
 * to throttle the calls.
 *
 * Concretely, after we processed a data point from a url,
 * we do not want to process any subseqent data points with the same url
 * until a timeout period is passed.
 *
 * eg:
 * timeout - 2
 * time     -  | 0 | 1 | 2 | 3 | 4 | 5 |
 * url      -  | a | a | b | a | b | b |
 * process? -  | y | n | y | y | n | y |
 *
 * Started: Nov 6 5:26PM
 *
 */
public class ThrottledProcessorTakeThree implements Processor {
  private final Processor processor;
  private final Clock clock;
  private final int timeout;
  
  public ThrottledProcessorTakeThree(Processor processor, Clock clock, int timeout) {
    this.processor = processor;
    this.clock = clock;
    this.timeout = timeout;
  }

  @Override
  public void process(String url, String payload) {
  }

  public static class ThrottledProcessorTest {
    @Test
    public void test1() {
      Processor processor = mock(Processor.class);
      Clock clock = mock(Clock.class);

      Processor throttledProcessor = new ThrottledProcessorTakeThree(
          processor,
          clock,
          2);

      when(clock.getTime()).thenReturn(1);

      throttledProcessor.process("abc", "some data");
      throttledProcessor.process("abc", "some moar data");
      throttledProcessor.process("abc", "lolz");

      verify(processor).process(eq("abc"), anyString());
      verify(processor).process("abc", "some data");
    }

    @Test
    public void test2() {
      Processor processor = mock(Processor.class);
      Clock clock = mock(Clock.class);

      Processor throttledProcessor = new ThrottledProcessorTakeThree(
          processor,
          clock,
          2);

      when(clock.getTime()).thenReturn(1);

      throttledProcessor.process("abc", "some data");
      throttledProcessor.process("abc", "some moar data");

      when(clock.getTime()).thenReturn(4);

      throttledProcessor.process("abc", "lolz");

      verify(processor, times(2)).process(eq("abc"), anyString());
      verify(processor).process("abc", "some data");
      verify(processor).process("abc", "lolz");
    }
  }
}
