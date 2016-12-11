import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Started 12/04 4:55pm
 */
public class MergeIntervals {
  public static class Interval {
    final int start;
    final int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
  }

  public List<Interval> merge(List<Interval> intervals) {
    intervals.sort(Comparator.comparingInt(interval -> interval.start));

    List<Interval> mergedIntervals = new ArrayList<>();

    for (Interval interval : intervals) {
      if (mergedIntervals.isEmpty() || !hasOverlap(mergedIntervals.get(mergedIntervals.size() - 1), interval)) {
        mergedIntervals.add(interval);
      } else {
        Interval lastMerged = mergedIntervals.get(mergedIntervals.size() - 1);
        mergedIntervals.set(mergedIntervals.size() - 1, new Interval(lastMerged.start, Math.max(lastMerged.end, interval.end)));
      }
    }

    return mergedIntervals;
  }

  public boolean hasOverlap(Interval interval1, Interval interval2) {
    return interval1.start <= interval2.start && interval1.end >= interval2.start
        || interval2.start <= interval1.start && interval2.end >= interval1.start;
  }
}