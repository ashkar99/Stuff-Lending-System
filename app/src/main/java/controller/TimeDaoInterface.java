package controller;

/**
 * Interface for managing and manipulating time in the system.
 */
public interface TimeDaoInterface {

  /**
   * Gets the current day in the system.
   *
   * @return The current day as an integer.
   *
   */
  int getCurrentDay();

  /**
   * Advances the system's day by one.
   */
  void advanceDay();

  /**
   * Advances the system's day by a specified number of days.
   *
   * @param days The number of days to advance.
   *
   */
  void advanceDays(int days);

}
