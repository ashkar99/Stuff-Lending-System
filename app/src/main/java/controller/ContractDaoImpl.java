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
    // Validate if the item can be lent and the borrower has enough funds
    isItemAvailableToLend(item);
    isEnoughFundsToBorrow(borrower.getCredits(), item.getCostPerDay() * (endDay - startDay));

    // Create a new immutable contract and add it to the item and lender's records
    ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
    item.addContract(newContract);
    lender.addContract(newContract);
  }

  @Override
  public boolean isItemAvailableToLend(Item item) {
    if (!item.isAvailable()) {
      throw new IllegalArgumentException("The item is not available for lending.");
    }
    return true;
  }

  @Override
  public boolean isEnoughFundsToBorrow(int borrowerFunds, int itemTotalCost) {
    if (borrowerFunds < itemTotalCost) {
      throw new IllegalArgumentException("Insufficient funds to borrow the item for the given days.");
    }
    return true;
  }
}
