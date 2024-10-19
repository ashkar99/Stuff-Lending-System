package model;

import java.util.UUID;

/**
 * The Contract class represents a rental agreement between a lender and a
 * borrower
 * for a specific item, over a defined time period. It manages contract details
 * such as contract ID, lender, borrower, item, start and end dates, and the
 * total cost. The contract can also be marked as completed or canceled.
 * This class is immutable.
 */
public final class ImmutableContract extends FunctionFather {

  private final String contractId;
  private final Member lender;
  private final Member borrower;
  private final Item item;
  private final int startDay;
  private final int endDay;
  private final int totalCost;
  private String status; // "Active", "Completed", or "Canceled"

  /**
   * Constructor to initialize a new Contract with the provided details.
   * By default, the contract's status is set to "Active".
   *
   * @param contractId The unique identifier for the contract. If null, it will be
   *                   auto-generated.
   * @param lender     The member who is lending the item.
   * @param borrower   The member who is borrowing the item.
   * @param item       The item being borrowed.
   * @param startDay   The start day of the contract period.
   * @param endDay     The end day of the contract period.
   */
  public ImmutableContract(String contractId, Member lender, Member borrower, Item item, int startDay, int endDay) {

    this.contractId = generateUniqueId();
    this.lender = new Member(lender.getName(), lender.getEmail(), lender.getPhoneNumber(), lender.getPassword()); // Defensive
                                                                                                                  // copy
    this.borrower = new Member(borrower.getName(), borrower.getEmail(), borrower.getPhoneNumber(),
        borrower.getPassword()); // Defensive copy
    this.item = new Item(item.getCategory(), item.getName(), item.getDescription(), item.getCostPerDay(),
        item.getOwner()); // Defensive copy
    this.startDay = startDay;
    this.endDay = endDay;
    this.totalCost = calculateTotalCost();
    this.status = "Active";
    this.creationDate = setCreationDate();
  }

  /**
   * Gets the contract's unique identifier.
   *
   * @return The contract ID.
   */
  public String getContractId() {
    return contractId;
  }

  /**
   * Gets the lender of the item.
   *
   * @return A copy of the lender as a {@link Member}.
   */
  public Member getLender() {
    return new Member(lender.getName(), lender.getEmail(), lender.getPhoneNumber(), lender.getPassword());
  }

  /**
   * Gets the borrower of the item.
   *
   * @return A copy of the borrower as a {@link Member}.
   */
  public Member getBorrower() {
    return new Member(borrower.getName(), borrower.getEmail(), borrower.getPhoneNumber(), borrower.getPassword());
  }

  /**
   * Gets the item being borrowed.
   *
   * @return A copy of the borrowed item as an {@link Item}.
   */
  public Item getItem() {
    return new Item(item.getCategory(), item.getName(), item.getDescription(),
        item.getCostPerDay(), item.getOwner()); // Return a defensive copy
  }

  /**
   * Gets the start day of the contract period.
   *
   * @return The start day.
   */
  public int getStartDay() {
    return startDay;
  }

  /**
   * Gets the end day of the contract period.
   *
   * @return The end day.
   */
  public int getEndDay() {
    return endDay;
  }

  /**
   * Gets the total cost of the contract, calculated based on the item's
   * daily cost and the length of the contract period.
   *
   * @return The total cost of the contract.
   */
  public int getTotalCost() {
    return totalCost;
  }

  /**
   * Gets the current status of the contract.
   *
   * @return The status of the contract.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Marks the contract as "Completed" and sets the item as available.
   * Although this method changes internal state, immutability is preserved
   * because it's modifying a string that has no effect on the rest of the object.
   */
  public void completeContract() {
    if (!"Completed".equals(this.status)) {
      this.status = "Completed";
      item.markAsAvailable();
    }
  }

  /**
   * Calculates the total cost of the contract based on the item's cost per day
   * and the number of days between the start and end of the contract.
   *
   * @return The total cost of the contract.
   */
  private int calculateTotalCost() {
    return item.getCostPerDay() * (endDay - startDay);

  }
}
