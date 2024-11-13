package controller;

import model.Item;
import model.Member;

/**
 * Interface for managing contracts related to lending items between members.
 */
public interface ContractDaoInterface {

  /**
   * Creates a new contract between a lender and a borrower for a specific item.
   *
   * @param lender   The member lending the item.
   * @param borrower The member borrowing the item.
   * @param item     The item being lent.
   * @param startDay The start day of the contract period.
   * @param endDay   The end day of the contract period.
   */
  void createContract();

  /**
   * Checks if the borrower has enough funds to borrow the item.
   *
   * @param borrowerFunds The funds available to the borrower.
   * @param itemCost      The cost of borrowing the item.
   * @return True if the borrower has enough funds, false otherwise.
   */
  boolean isEnoughFundsToBorrow(Member borrowe,Item item, int startDay, int endDay);
}
