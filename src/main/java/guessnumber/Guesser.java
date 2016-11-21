package guessnumber;

/**
 * Created by yau on 11/15/16.
 */
public abstract class Guesser {
  private final int number;

  public Guesser(int number) {
    this.number = number;
  }

  public String guess(int number) {
    if (number > this.number) {
      return "Too high!";
    } else if (number < this.number) {
      return "Too low!";
    } else {
      return "Correct!";
    }
  }

  public abstract int getNumber();
}
