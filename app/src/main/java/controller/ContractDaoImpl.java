package controller;

import model.ImmutableContract;
import model.Item;
import model.Member;

/**
 * Implementation of ContractDaoInterface to manage the creation and validation of contracts.
 */
public class ContractDaoImpl implements ContractDaoInterface {
  private TimeDaoInterface timeDao = new TimeDaoImpl();

  @Override
  public void createContract(Member lender, Member borrower, Item item, int startDay, int endDay) {
    try {
      if (!isItemAvailableToLent(item)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
      }
      if (!isEnoughFundsToBorrow(borrower.getCredits(), item.getCostPerDay() * (endDay - startDay))) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
      }

      ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
      item.addContract(newContract);
      lender.addContract(newContract);

      // Contract successfully created, message can be handled in the view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error creating contract: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
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
