package controller;

import model.Time;

/**
 * TimeDaoImpl class for handling time-related operations.
 */
public class TimeDaoImpl implements TimeDaoInterface {
  public Time time = new Time(0);

  public TimeDaoImpl() {
  }

  @Override
  public void advanceDay() {
    time.advanceDay();
  }

  @Override
  public void advanceDays(int numberOfDays) {
    time.advanceDays(numberOfDays);
  }

  @Override
  public int getCurrentDay() {
    return time.getCurrentDay();
  }
}
