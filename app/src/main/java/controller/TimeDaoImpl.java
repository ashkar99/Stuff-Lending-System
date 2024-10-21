package controller;

import model.Time;

/**
 * TimeDaoImpl class for handling time-related operations.
 */
public class TimeDaoImpl implements TimeDaoInterface {
  private Time time = new Time(0);

  /**
   * Advances the day by one. This simulates moving to the next day in the system.
   * The current day will be incremented by 1.
   */
  @Override
  public void advanceDay() {
    try {
      int dayCounter = time.getCurrentDay();
      Time newTime = new Time(++dayCounter); // Increment day counter
      this.time = newTime;
      System.out.println("The day has advanced to: " + dayCounter);
    } catch (Exception e) {
      throw new RuntimeException("Error advancing the day.", e);
    }
  }

  /**
   * Advances the day by a specified number of days. This allows for skipping
   * multiple days.
   *
   * @param numberOfDays The number of days to advance. Must be a positive
   *                     integer.
   * @throws IllegalArgumentException if the number of days is less than or equal
   *                                  to 0.
   */
  @Override
  public void advanceDays(int numberOfDays) {
    try {
      if (numberOfDays <= 0) {
        throw new IllegalArgumentException("Number of days must be positive.");
      }

      int dayCounter = time.getCurrentDay();
      dayCounter += numberOfDays;
      Time newTime = new Time(dayCounter);
      this.time = newTime;
      System.out.println("The day has advanced to: " + dayCounter);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error advancing days: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while advancing days.", e);
    }
  }

  /**
   * Returns the current day in the system.
   *
   * @return The current day as an integer.
   */
  @Override
  public int getCurrentDay() {
    try {
      return time.getCurrentDay();
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving the current day.", e);
    }
  }
}
