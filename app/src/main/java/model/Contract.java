package model;

public class Contract {
    private int startDate;
    private int endDate;
    private Item item;
    private Member borrower;
    private Member lender;

    public Contract(int startDate, int endDate, Item item, Member borrower, Member lender) {
        setStartDate(startDate);
        setEndDate(endDate);
        setItem(item);
        setBorrower(borrower);
        setLender(lender);
    }
    public int getStartDate() {
        return startDate;
    }
    private void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public int getEndDate() {
        return endDate;
    }
    private void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    public Item getItem() {
        return item;
    }
    private void setItem(Item item) {
        this.item = item;
    }
    public Member getBorrower() {
        return borrower;
    }
    private void setBorrower(Member borrower) {
        this.borrower = borrower;
    }
    public Member getLender() {
        return lender;
    }
    private void setLender(Member lender) {
        this.lender = lender;
    }

}
