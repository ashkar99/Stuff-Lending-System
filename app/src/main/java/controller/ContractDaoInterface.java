package controller;

/**
 * Interface for managing contracts related to lending items between members.
 */
public interface ContractDaoInterface {

  /**
   * Creates a new contract between a lender and a borrower for a specific item.
   */
  void createContract();
}
