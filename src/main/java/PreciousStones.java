import org.junit.Test;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class PreciousStones {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    long numInputs = scanner.nextLong();
    String[] inputs = Stream.generate(scanner::next).limit(numInputs).toArray(String[]::new);

    Stream.of(inputs).map(PreciousStones::minB).forEach(System.out::println);
  }

  private static class IntPair {
    public final int left, right;

    IntPair(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  public static int minB(String chain) {
    IntPair longestChain = calculateB(chain);

    if (longestChain == null) {
      return 0;
    }

    int swapIdx = longestChain.left + (longestChain.right - 1) / 2;
    String newChain =
        chain.substring(0, swapIdx) +
        (chain.charAt(swapIdx) == 'A' ? 'R' : 'A') +
        chain.substring(swapIdx + 1);

    return calculateB(newChain).right;
  }

  public static IntPair calculateB(String chain) {
    if (chain.length() == 0) {
      return null;
    }

    int longestChainIdx = 0;
    int longestChainLength = 0;

    int firstChainIdx;
    int firstChainLength = 0;

    int lastChainIdx = 0;
    int lastChainLength = 0;

    int currentChainIdx = 0;

    int numChainsFound = 0;

    for (int i = 1; i <= chain.length(); i++) {
      if (chain.length() == i || chain.charAt(i) != chain.charAt(i - 1)) {
        final int currentChainLength = i - currentChainIdx;

        if (numChainsFound == 0) {
          firstChainLength = currentChainLength;
        }

        if (currentChainLength > longestChainLength) {
          longestChainIdx = currentChainIdx;
          longestChainLength = currentChainLength;
        }

        lastChainIdx = currentChainIdx;
        lastChainLength = currentChainLength;

        numChainsFound++;
        currentChainIdx = i;
      }
    }

    if (numChainsFound > 1 && chain.charAt(0) == chain.charAt(chain.length() - 1)) {
      firstChainIdx = lastChainIdx;
      firstChainLength += lastChainLength;

      if (firstChainLength > longestChainLength) {
        longestChainIdx = firstChainIdx;
        longestChainLength = firstChainLength;
      }
    }

    return new IntPair(longestChainIdx, longestChainLength);
  }

  public static class PreciousStonesTest {
    @Test
    public void test0() {
      assertEquals(3, calculateB("AAA").right);
    }

    @Test
    public void test1() {
      assertEquals(2, calculateB("ARA").right);
    }

    @Test
    public void test2() {
      assertEquals(3, minB("RRRAAAARAA"));
    }

    @Test
    public void test3() {
      assertEquals(4, minB("ARRRRAAA"));
    }
  }
}
