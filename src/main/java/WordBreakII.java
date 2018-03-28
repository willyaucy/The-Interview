import java.util.*;
import java.util.stream.Collectors;

class WordBreakII {
  public List<String> wordBreak(String s, List<String> wordDict) {
    return wordBreak(s, wordDict, new HashMap<>());
  }

  public List<String> wordBreak(String s, List<String> wordDict, Map<String, List<String>> memo) {
    return memo.computeIfAbsent(s, key -> {
      List<String> output = new LinkedList<>();

      for (String word : wordDict) {
        if (s.equals(word)) {
          output.add(word);
        } else if (s.startsWith(word)) {
          output.addAll(
              wordBreak(s.substring(word.length()), wordDict, memo).stream()
                  .map(sentence -> word + " " + sentence)
                  .collect(Collectors.toList())
          );
        }
      }

      return output;
    });
  }
}