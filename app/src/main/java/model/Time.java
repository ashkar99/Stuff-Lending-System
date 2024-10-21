package model;

/**
 * The Time class represents a simple time tracking system, where the "current
 * day" is incremented
 * based on the operations performed. This class allows for advancing days and
 * retrieving the current day.
 */
public class Time {
  private int dayCounter;

  /**
   * Constructor to initialize the Time object with a day counter starting at 0.
   */
  public Time(int dayCounter) {
    setCurrentDay(dayCounter);
  }

  /**
   * Gets the current day, which is represented by the dayCounter.
   *
   * @return The current day as an integer.
   *
   */
  public int getCurrentDay() {
    return dayCounter;
  }

  public void setCurrentDay(int dayCounter) {
    this.dayCounter = dayCounter;
  }
}
