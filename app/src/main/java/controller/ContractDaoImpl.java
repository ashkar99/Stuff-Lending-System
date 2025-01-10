package controller;

import model.ImmutableContract;
import model.Item;
import model.Member;
import view.ContractViewer;
import view.FeedbackMessage;

/**
 * Implementation of ContractDaoInterface to manage the creation and validation
 * of contracts.
 */
public class ContractDaoImpl implements ContractDaoInterface {
  private TimeDaoInterface timeDao = new TimeDaoImpl();
  private ContractViewer contractViewer = new ContractViewer();
  private MemberDaoInterface memberDao = new MemberDaoImpl();
  private ItemDaoInterface itemDao = new ItemDaoImpl(memberDao);

  @Override
  public void createContract() {

    String[] contractStrings = contractViewer.createContract();
    Member lender = memberDao.getMemberById(contractStrings[0]);
    Member borrower = memberDao.getMemberById(contractStrings[1]);

    // Null check for lender and borrower
    if (lender == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    if (borrower == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    // Fetch the item after verifying that the lender is not null
    Item item = itemDao.getItemById(lender, contractStrings[2]);

    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    int startDay = Integer.parseInt(contractStrings[3]);
    int endDay = Integer.parseInt(contractStrings[4]);

    int currentDay = timeDao.getCurrentDay(); // Get the current day from the TimeDao

    // Check if the start day is in the past
    if (startDay < currentDay) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_CONTRACT_INVALID_TIME.getMessage());
    }

    // Check if the item is available for the given time period
    if (!item.isAvailableForPeriod(startDay, endDay)) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
    }

    // Check if the borrower has enough funds
    if (!isEnoughFundsToBorrow(borrower, item, startDay, endDay)) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
    }

    // Create the contract and update the item and lender
    ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
    borrower.updateCredits(-item.getCostPerDay() * (endDay - startDay + 1));
    lender.updateCredits(item.getCostPerDay() * (endDay - startDay + 1));
    item.addContract(newContract);
    lender.addContract(newContract);
    contractViewer.displayFeedback(true, FeedbackMessage.SUCCESS_CONTRACT_CREATION.getMessage(), null);

  }

  @Override
  public boolean isEnoughFundsToBorrow(Member borrower, Item item, int startDay, int endDay) {

    int borrowerFunds = borrower.getCredits();
    int itemTotalCost = item.getCostPerDay() * (endDay - startDay + 1);
    if (borrowerFunds < itemTotalCost) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
    }
    return true;
  }
}
