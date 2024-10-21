package controller;

import model.Time;

/**
 * TimeDaoImpl class for handling time-related operations.
 */
public class TimeDaoImpl implements TimeDaoInterface {
  private Time time = new Time(0);

  @Override
  public void advanceDay() {
    try {
      int dayCounter = time.getCurrentDay();
      Time newTime = new Time(++dayCounter);  // Increment day counter
      this.time = newTime;

      // Time successfully advanced, this message can be handled in the view.
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

      int dayCounter = time.getCurrentDay();
      dayCounter += numberOfDays;
      Time newTime = new Time(dayCounter);
      this.time = newTime;

      // Time successfully advanced, this message can be handled in the view.
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
