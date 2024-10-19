package controller;

import model.Time;

public class TimeDaoImpl implements TimeDaoInterface {

  private Time time = new Time(0);

  /**
   * Advances the day by one. This simulates moving to the next day in the system.
   * The current day will be incremented by 1.
   */
  public void advanceDay() {

    int dayCounter = time.getCurrentDay();
    Time newtime = new Time(dayCounter++);
    this.time = newtime;
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
      int dayCounter = time.getCurrentDay();
      dayCounter += numberOfDays;
      Time newtime = new Time(dayCounter);
      this.time = newtime;
      System.out.println("Dagen har avancerats till: " + dayCounter);
    } else {
      System.out.println("Antalet dagar måste vara positivt.");
    }
  }

  @Override
  public int getCurrentDay() {
    int dayCounter = time.getCurrentDay();
   return dayCounter;
  }
}
