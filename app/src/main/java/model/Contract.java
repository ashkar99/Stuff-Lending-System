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

  public Contract(String contractId, Member lender, Member borrower, Item item, int startDay, int endDay) {
    setContractId(contractId);
    setLender(lender);
    setBorrower(borrower);
    setItem(item);
    setStartDay(startDay);
    setEndDay(endDay);
    setTotalCost(totalCost);
    this.status = "Active";
  }

  public String getContractId() {
    return contractId;
  }

  private void setContractId(String contractId) {
    if (contractId == null) {
      this.contractId = "CON" + System.nanoTime() % 1000000;
    } else {
      this.contractId = contractId;
    }
  }

  public Member getLender() {
    return lender;
  }

  private void setLender(Member lender) {
    this.lender = lender;
  }

  public Member getBorrower() {
    return borrower;
  }

  private void setBorrower(Member borrower) {
    this.borrower = borrower;
  }

  public Item getItem() {
    return item;
  }

  private void setItem(Item item) {
    this.item = item;
  }

  public int getStartDay() {
    return startDay;
  }

  private void setStartDay(int startDay) {
    this.startDay = startDay;
  }

  public int getEndDay() {
    return endDay;
  }

  private void setEndDay(int endDay) {
    this.endDay = endDay;
  }

  public int getTotalCost() {
    return totalCost;
  }

  private void setTotalCost(int totalCost) {
    totalCost = calculateTotalCost();
  }

  // Private Methods
  private int calculateTotalCost() {
    return item.getCostPerDay() * (endDay - startDay);
  }

  public void completeContract() {
    this.status = "Completed";
    item.markAsAvailable();
  }

}