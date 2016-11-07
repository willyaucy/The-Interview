package googleinterview.throttle;

import java.util.Optional;

/**
 * Given a list of rectangles,
 * see if you can form a big rectangle with all of them with no overlap or empty space.
 */
public class RectangleMerge {
  public static class Rectangle {
    public final int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }
  }

  public Optional<Rectangle> mergeRectangle(Rectangle[] rectangles) {
    return Optional.empty();
  }
}
