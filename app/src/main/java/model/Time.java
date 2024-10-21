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
    setDayCounter(dayCounter);
  }

  /**
   * Gets the current day, which is represented by the dayCounter.
   *
   * @return The current day as an integer.
   */
  public int getCurrentDay() {
    return dayCounter;
  }

  /**
   * Set dayCounter.
   *
   * @param dayCounter set dayCounteer.
   */
  private void setDayCounter(int dayCounter) {
    this.dayCounter = dayCounter;
  }

  /**
   * Advances the current day by 1.
   */
  public void advanceDay() {
    dayCounter++;
  }

  /**
   * Advances the current day by a specific number of days.
   *
   * @param days The number of days to advance.
   */
  public void advanceDays(int days) {
    if (days > 0) {
      dayCounter += days;
    } else {
      throw new IllegalArgumentException("Days to advance must be greater than 0.");
    }
  }
}
