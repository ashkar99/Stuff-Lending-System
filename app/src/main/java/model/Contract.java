// package model;

// /**
//  * The Contract class represents a rental agreement between a lender and a
//  * borrower
//  * for a specific item, over a defined time period. It manages contract details
//  * such as contract ID, lender, borrower, item, start and end dates, and the
//  * total cost.
//  * The contract can also be marked as completed or canceled.
//  */
// public class Contract {

//   private String contractId;
//   private Member lender;
//   private Member borrower;
//   private Item item;
//   private int startDay;
//   private int endDay;
//   private int totalCost;
//   private String status; // "Active", "Completed", or "Canceled"

//   /**
//    * Constructor to initialize a new Contract with the provided details.
//    * By default, the contract's status is set to "Active".
//    *
//    * @param contractId The unique identifier for the contract. If null, it will be
//    *                   auto-generated.
//    * @param lender     The member who is lending the item.
//    * @param borrower   The member who is borrowing the item.
//    * @param item       The item being borrowed.
//    * @param startDay   The start day of the contract period.
//    * @param endDay     The end day of the contract period.
//    */
//   public Contract(String contractId, Member lender, Member borrower, Item item, int startDay, int endDay) {
//     setContractId(contractId);
//     setLender(lender);
//     setBorrower(borrower);
//     setItem(item);
//     setStartDay(startDay);
//     setEndDay(endDay);
//     setTotalCost();
//     this.status = "Active";
//   }

//   /**
//    * Gets the contract's unique identifier.
//    *
//    * @return The contract ID.
//    */
//   public String getContractId() {
//     return contractId;
//   }

//   /**
//    * Sets the contract ID. If the provided ID is null, an ID is auto-generated
//    * using the current time in nanoseconds.
//    *
//    * @param contractId The contract ID to set.
//    */
//   private void setContractId(String contractId) {
//     if (contractId == null) {
//       this.contractId = "CON" + System.nanoTime() % 1000000;
//     } else {
//       this.contractId = contractId;
//     }
//   }

//   /**
//    * Gets the lender of the item.
//    *
//    * @return The lender as a {@link Member}.
//    */
//   public Member getLender() {
//     return new Member(lender.getName(), lender.getEmail(), lender.getPhoneNumber(), lender.getPassword());
//   }

//   /**
//    * Sets the lender for the contract.
//    *
//    * @param lender The member lending the item.
//    */
//   private void setLender(Member lender) {
//     this.lender = lender;
//   }

//   /**
//    * Gets the borrower of the item.
//    *
//    * @return The borrower as a {@link Member}.
//    */
//   public Member getBorrower() {
//     return new Member(borrower.getName(), borrower.getEmail(), borrower.getPhoneNumber(), borrower.getPassword());
//   }

//   /**
//    * Sets the borrower for the contract.
//    *
//    * @param borrower The member borrowing the item.
//    */
//   private void setBorrower(Member borrower) {
//     this.borrower = borrower;
//   }

//   /**
//    * Gets the item being borrowed.
//    *
//    * @return The borrowed item as an {@link Item}.
//    */
//   public Item getItem() {
//     return new Item(item.getCategory(), item.getName(), item.getDescription(),
//         item.getCostPerDay(), item.getOwner());
//   }

//   /**
//    * Sets the item for the contract.
//    *
//    * @param item The item being borrowed.
//    */
//   private void setItem(Item item) {
//     this.item = item;
//   }

//   /**
//    * Gets the start day of the contract period.
//    *
//    * @return The start day.
//    */
//   public int getStartDay() {
//     return startDay;
//   }

//   /**
//    * Sets the start day of the contract period.
//    *
//    * @param startDay The start day to set.
//    */
//   private void setStartDay(int startDay) {
//     this.startDay = startDay;
//   }

//   /**
//    * Gets the end day of the contract period.
//    *
//    * @return The end day.
//    */
//   public int getEndDay() {
//     return endDay;
//   }

//   /**
//    * Sets the end day of the contract period.
//    *
//    * @param endDay The end day to set.
//    */
//   private void setEndDay(int endDay) {
//     this.endDay = endDay;
//   }

//   /**
//    * Gets the total cost of the contract, calculated based on the item's
//    * daily cost and the length of the contract period.
//    *
//    * @return The total cost of the contract.
//    */
//   public int getTotalCost() {
//     return totalCost;
//   }

//   /**
//    * Sets the total cost for the contract. The cost is calculated by multiplying
//    * the item's daily cost by the number of days in the contract period.
//    */
//   private void setTotalCost() {
//     this.totalCost = calculateTotalCost();
//   }

//   /**
//    * get status.
//    *
//    * @return the status of the contract.
//    *
//    */
//   public String getStatus() {
//     return status;
//   }

//   /**
//    * Calculates the total cost of the contract based on the item's cost per day
//    * and the number of days between the start and end of the contract.
//    *
//    * @return The total cost of the contract.
//    */
//   private int calculateTotalCost() {
//     return item.getCostPerDay() * (endDay - startDay);
//   }

//   /**
//    * Marks the contract as "Completed" and sets the item as available.
//    */
//   public void completeContract() {
//     this.status = "Completed";
//     item.markAsAvailable();
//   }

// }
