package model;

public class Contract {
  private String contractId;
  private Member lender;
  private Member borrower;
  private Item item;
  private int startDay;
  private int endDay;
  private int totalCost;
  private String status; // "Active", "Completed", or "Canceled"

  // Constructor
  public Contract(Member lender, Member borrower, Item item, int startDay, int endDay) {
    this.contractId = generateContractId();
    this.lender = lender;
    this.borrower = borrower;
    this.item = item;
    this.startDay = startDay;
    this.endDay = endDay;
    this.totalCost = calculateTotalCost();
    this.status = "Active";
  }

  // Getters and Setters
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getTotalCost() {
    return totalCost;
  }

  // Private Methods
  private int calculateTotalCost() {
    return item.getCostPerDay() * (endDay - startDay);
  }

  private String generateContractId() {
    return "CON" + System.nanoTime() % 1000000;
  }

  public void completeContract() {
    this.status = "Completed";
    item.markAsAvailable();
  }

  public int getEndDay() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEndDay'");
  }
}