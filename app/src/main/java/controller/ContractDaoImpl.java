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
  private TimeDaoInterface timeDao = new TimeDaoImpl(); // Using TimeDao to check the current day
  private ContractViewer contractViewer = new ContractViewer();
  private MemberDaoInterface memberDao = new MemberDaoImpl();
  private ItemDaoInterface itemDao = new ItemDaoImpl(memberDao);

  @Override
  public void createContract( ) {
    try {
      String[] contractStrings = contractViewer.createContract();
      Member lender = memberDao.getMemberById(contractStrings[0]);
      Member borrower = memberDao.getMemberById(contractStrings[1]);
      Item item = itemDao.getItemById(lender, contractStrings[2]);
      int startDay = Integer.parseInt(contractStrings[3]);
      int endDay = Integer.parseInt(contractStrings[4]);

      int currentDay = timeDao.getCurrentDay(); // Get the current day from the TimeDao

      if (lender == null || borrower == null || item == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      // Check if the start day is in the past
      if (startDay < currentDay) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_CONTRACT_INVALID_TIME.getMessage());
      }

      // Check if the item is available for the given time period
      if (!item.isAvailableForPeriod(startDay, endDay)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
      }

      // Check if the borrower has enough funds
      if (!isEnoughFundsToBorrow(borrower,item, startDay,endDay)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
      }

      // Create the contract and update the item and lender
      ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
      borrower.updateCredits(-item.getCostPerDay() * (endDay - startDay + 1));
      lender.updateCredits(item.getCostPerDay() * (endDay - startDay + 1));
      item.addContract(newContract);
      lender.addContract(newContract);

      // Contract successfully created
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error creating contract: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public boolean isEnoughFundsToBorrow(Member borrower,Item item, int startDay, int endDay) {
    try {
      int borrowerFunds= borrower.getCredits();
      int itemTotalCost = item.getCostPerDay() * (endDay - startDay + 1);
      if (borrowerFunds < itemTotalCost) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
      }
      return true;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error checking borrower's funds: " + e.getMessage(), e);
    }
  }
}
