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
  public Time() {
    this.dayCounter = 0;
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

  /**
   * Advances the day by one. This simulates moving to the next day in the system.
   * The current day will be incremented by 1.
   */
  public void advanceDay() {
    dayCounter++;
    System.out.println("Dagen har avancerats till: " + dayCounter);
  }

  /**
   * Advances the day by a specified number of days. This allows for skipping
   * multiple days.
   *
   * @param numberOfDays The number of days to advance. Must be a positive
   *                     integer.
   * @throws IllegalArgumentException if the number of days is less than or equal
   *                                  to 0.
   *
   */
  public void advanceDays(int numberOfDays) {
    if (numberOfDays > 0) {
      dayCounter += numberOfDays;
      System.out.println("Dagen har avancerats till: " + dayCounter);
    } else {
      System.out.println("Antalet dagar måste vara positivt.");
    }
  }
}
