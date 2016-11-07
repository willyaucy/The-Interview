package googleinterview.throttle;

import org.junit.Test;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
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
 * Started: Oct 23 4:56PM
 * Finished: Oct 23 5:21PM (not verified)
 * Finished: Oct 23 5:57PM (test cases done, verified)
 *
 */
public class ThrottledProcessor implements Processor {
  private final Processor processor;
  private final Clock clock;
  private final long timeout;

  private HashMap<String, Integer> lastProcessed = new HashMap<>();
  private LinkedList<String> urlsOnCooldown = new LinkedList<>();

  public ThrottledProcessor(Processor processor, Clock clock, long timeout) {
    this.processor = processor;
    this.clock = clock;
    this.timeout = timeout;
  }

  @Override
  public void process(String url, String payload) {
    if (!lastProcessed.containsKey(url) ||
        clock.getTime() > lastProcessed.get(url) + timeout) {

      processor.process(url, payload);

      lastProcessed.put(url, clock.getTime());
      urlsOnCooldown.add(url);
    }

    cleanupQueue();
  }

  private void cleanupQueue() {
    while (!urlsOnCooldown.isEmpty()
        && clock.getTime() > lastProcessed.get(urlsOnCooldown.peek()) + timeout) {

      lastProcessed.remove(urlsOnCooldown.remove());
    }
  }

  public static class ThrottledProcessorTest {
    @Test
    public void test1() {
      Processor processor = mock(Processor.class);
      Clock clock = mock(Clock.class);

      ThrottledProcessor throttledProcessor = new ThrottledProcessor(
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

      ThrottledProcessor throttledProcessor = new ThrottledProcessor(
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
