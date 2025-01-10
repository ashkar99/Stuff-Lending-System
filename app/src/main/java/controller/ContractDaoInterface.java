package controller;

import model.Item;
import model.Member;

/**
 * Interface for managing contracts related to lending items between members.
 */
public interface ContractDaoInterface {

  /**
   * Creates a new contract between a lender and a borrower for a specific item.
   */
  void createContract();

  /**
   * Check if borrower have enough credits to lend the item.
   *
   * @param borrowe  borrower.
   * @param item     item.
   * @param startDay staartday.
   * @param endDay   endadya.
   */
  boolean isEnoughFundsToBorrow(Member borrowe, Item item, int startDay, int endDay);
}
