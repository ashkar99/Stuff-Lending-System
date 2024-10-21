package controller;

import model.ImmutableContract;
import model.Item;
import model.Member;

/**
 * Implementation of ContractDaoInterface to manage the creation and validation of contracts.
 */
public class ContractDaoImpl implements ContractDaoInterface {

  @Override
  public void createContract(Member lender, Member borrower, Item item, int startDay, int endDay) {
    try {
      // Validate if the item can be lent and the borrower has enough funds
      isItemAvailableToLent(item);
      isEnoughFundsToBorrow(borrower.getCredits(), item.getCostPerDay() * (endDay - startDay));

      // Create a new immutable contract and add it to the item and lender's records
      ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
      item.addContract(newContract);
      lender.addContract(newContract);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error while creating contract: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while creating the contract.", e);
    }
  }

  @Override
  public boolean isItemAvailableToLent(Item item) {
    try {
      if (!item.isAvailable()) {
        throw new IllegalArgumentException("The item is not available");
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
        throw new IllegalArgumentException("Not enough funds to borrow the item for the given days");
      }
      return true;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error checking borrower's funds: " + e.getMessage(), e);
    }
  }
}
