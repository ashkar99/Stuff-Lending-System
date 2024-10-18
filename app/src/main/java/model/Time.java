package model;

public class Time {
  private int dayCounter;

  public Time() {
    this.dayCounter = 0;
  }
  
  
  public int getCurrentDay() {
    return dayCounter;
  }

  public void advanceDay() {
    dayCounter++;
    System.out.println("Dagen har avancerats till: " + dayCounter);
  }

  public void advanceDays(int numberOfDays) {
    if (numberOfDays > 0) {
      dayCounter += numberOfDays;
      System.out.println("Dagen har avancerats till: " + dayCounter);
    } else {
      System.out.println("Antalet dagar måste vara positivt.");
    }
  }
}
