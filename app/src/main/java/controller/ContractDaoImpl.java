package controller;

import model.ImmutableContract;
import model.Item;
import model.Member;

/**
 * Implementation of ContractDaoInterface to manage the creation and validation
 * of contracts.
 */
public class ContractDaoImpl implements ContractDaoInterface {
  private TimeDaoInterface timeDao = new TimeDaoImpl(); // Using TimeDao to check the current day

  @Override
  public void createContract(Member lender, Member borrower, Item item, int startDay, int endDay) {
    try {
      int currentDay = timeDao.getCurrentDay(); // Get the current day from the TimeDao

      // Check if the start day is in the past
      if (startDay < currentDay) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_CONTRACT_INVALID_TIME.getMessage());
      }

      // Check if the item is available for the given time period
      if (!isItemAvailableToLend(item, startDay, endDay)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
      }

      // Check if the borrower has enough funds
      if (!isEnoughFundsToBorrow(borrower.getCredits(), item.getCostPerDay() * (endDay - startDay))) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
      }

      // Create the contract and update the item and lender
      ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
      item.addContract(newContract);
      lender.addContract(newContract);

      // Contract successfully created
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error creating contract: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  /**
   * Checks if the item is available for the given time period (startDay to
   * endDay).
   *
   * @param item     The item to check.
   * @param startDay The start day of the contract.
   * @param endDay   The end day of the contract.
   * @return True if the item is available, false otherwise.
   */
  private boolean isItemAvailableToLend(Item item, int startDay, int endDay) {
    try {
      for (ImmutableContract contract : item.getContracts()) {
        // Check if the contract overlaps with the desired period
        if ((startDay >= contract.getStartDay() && startDay <= contract.getEndDay())
            || (endDay >= contract.getStartDay() && endDay <= contract.getEndDay())) {
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      throw new RuntimeException("Error checking item availability: " + e.getMessage(), e);
    }
  }

  @Override
  public boolean isItemAvailableToLent(Item item) {
    try {
      if (!item.isAvailable()) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
      }
      return true;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error checking item availability: " + e.getMessage(), e);
    }
  }

  @Override
  public boolean isEnoughFundsToBorrow(int borrowerFunds, int itemTotalCost) {
    try {
      if (borrowerFunds < itemTotalCost) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
      }
      return true;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error checking borrower's funds: " + e.getMessage(), e);
    }
  }
}
