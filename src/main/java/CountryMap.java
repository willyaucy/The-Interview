import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountryMap {
  private static class Area {
    public final int row;
    public final int col;

    Area(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Area
          && ((Area) o).row == row
          && ((Area) o).col == col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public int solution(int[][] A) {
    int numCountries = 0;
    boolean[][] visited = new boolean[A.length][A[0].length];

    for (int row = 0; row < A.length; row++) {
      for (int col = 0; col < A[0].length; col++) {
        if (!visited[row][col]) {
          numCountries++;

          LinkedList<Area> areasToVisit = new LinkedList<>();
          areasToVisit.add(new Area(row, col));

          while (!areasToVisit.isEmpty()) {
            Area area = areasToVisit.remove();
            visited[area.row][area.col] = true;
            areasToVisit.addAll(findConnectedAreas(A, visited, area.row, area.col));
          }
        }
      }
    }

    return numCountries;
  }

  private static Set<Area> findConnectedAreas(int[][] map, boolean[][] visited, int row, int col) {
    return Stream.of(new Area(row + 1, col), new Area(row - 1, col), new Area(row, col + 1), new Area(row, col - 1))
        .filter(area ->
            area.row >= 0 && area.row < map.length &&
                area.col >= 0 && area.col < map[0].length &&
                !visited[area.row][area.col] &&
                map[row][col] == map[area.row][area.col])
        .distinct()
        .collect(Collectors.toSet());
  }
}
