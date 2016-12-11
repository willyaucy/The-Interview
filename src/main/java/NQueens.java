import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NQueens {
  public static class IntPair {
    public final int left, right;

    IntPair(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  public static class Chessboard {
    public static final char QUEEN = 'Q';
    public static final char EMPTY = '.';

    private final int size;
    private final char[][] board;
    private IntPair[] validNextPositions;

    Chessboard(int n) {
      size = n;
      board = new char[n][n];
      validNextPositions = IntStream.range(0, size)
          .mapToObj(left -> IntStream.range(0, size).mapToObj(right -> new IntPair(left, right)))
          .flatMap(stream -> stream).toArray(IntPair[]::new);

      for (char[] row : board) {
        Arrays.fill(row, EMPTY);
      }
    }

    private boolean canAddQueen(int x, int y) {
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (board[i][j] == QUEEN && (i == x || j == y || Math.abs(i - x) == Math.abs(j - y))) {
            return false;
          }
        }
      }

      return true;
    }

    public Chessboard addQueen(int x, int y) {
      if (!canAddQueen(x, y)) {
        throw new IllegalArgumentException();
      }

      Chessboard newChessboard = new Chessboard(size);

      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          newChessboard.board[i][j] = board[i][j];
        }
      }

      newChessboard.board[x][y] = QUEEN;
      newChessboard.validNextPositions = Stream.of(validNextPositions)
          .filter(position -> newChessboard.canAddQueen(position.left, position.right))
          .toArray(IntPair[]::new);

      return newChessboard;
    }

    public List<IntPair> getValidNextPositions() {
      return Collections.unmodifiableList(Arrays.asList(validNextPositions));
    }

    public List<String> getState() {
      return Stream.of(board).map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Chessboard && Arrays.deepEquals(board, ((Chessboard) o).board);
    }

    @Override
    public int hashCode() {
      return Arrays.deepHashCode(board);
    }
  }

  public List<List<String>> solveNQueens(int n) {
    return Stream.iterate(
        Collections.singletonList(new Chessboard(n)),
        boards -> boards.stream()
            .flatMap(board -> board.getValidNextPositions()
            .stream()
            .map(position -> board.addQueen(position.left, position.right)))
            .collect(Collectors.toList()))
        .skip(n).findFirst().get().stream().map(Chessboard::getState).collect(Collectors.toList());
  }

  public static class NQueensTest {
    @Test
    public void test0() {
      System.out.println(Arrays.deepToString(new NQueens().solveNQueens(4).stream().map(states -> states.toArray()).toArray()));
    }
  }
}
