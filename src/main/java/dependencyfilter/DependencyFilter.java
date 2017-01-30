package dependencyfilter;

import java.util.Collection;
import java.util.List;

/**
 * apple -> banana -> carrot -> danish
 *     |        |                   ^
 *     |        V                   |
 *     \----> bagel ---------------/
 *
 * Topologically sorted list of dependencies:
 * apple
 * banana
 * carrot
 * bagel
 * danish
 *
 * input 1 (root):
 * carrot
 *
 * input 2 (list of inputs)
 * carrot
 * cheese
 * danish
 * donut
 *
 * expected output (clean list):
 * carrot
 * danish
 */
public class DependencyFilter {
  /**
   * Takes in an input list, returns a filtered list without nondependencies in there.
   *
   * depth first search to find the node (# nodes ^ 2)
   * add all dependencies to a hashset (# nodes ^ 2)
   * loop thru list of inputs, filter things not in the set (# inputs)
   *
   * time complexity (# nodes ^ 2)
   * space complexity (# nodes ^ 2)
   */
  List<String> filterDependencies(String root, StringGraph dependencyGraph, List<String> inputList) {
    return null;
  }

  List<String> filterDependencies(Collection<String> roots, StringGraph dependencyGraph, List<String> inputList) {
    return null;
  }

  private static class StringGraph {
    String value;
    List<StringGraph> children;

    public StringGraph(String value, List<StringGraph> children) {
      this.value = value;
      this.children = children;
    }
  }
}