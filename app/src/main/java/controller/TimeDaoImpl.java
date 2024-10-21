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
    try {
      time.advanceDay();
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void advanceDays(int numberOfDays) {
    try {
      if (numberOfDays <= 0) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_NEGATIVE_VALUE.getMessage());
      }

      time.advanceDays(numberOfDays);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_NEGATIVE_VALUE.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public int getCurrentDay() {
    try {
      return time.getCurrentDay(); 
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }
}
