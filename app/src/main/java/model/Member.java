package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Member {
  private String memberId;
  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private int credits = 0;
  private LocalDate creationDate;
  private List<Item> itemsOwned = new ArrayList<>();
  private List<Contract> lendingHistory = new ArrayList<>();

  // Constructor
  public Member(String name, String email, String phoneNumber, String password) {
    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email format.");
    }
    if (!isValidPhoneNumber(phoneNumber)) {
      throw new IllegalArgumentException("Invalid phone number format.");
    }

    setMemberId(email);
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    this.creationDate = LocalDate.now(); 
  }

  public Member(String name, String email, String phoneNumber, String password, LocalDate creationDate) {
    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email format.");
    }
    if (!isValidPhoneNumber(phoneNumber)) {
      throw new IllegalArgumentException("Invalid phone number format.");
    }

    setMemberId(email);
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    setCreationDate(creationDate);
  }

  public String getMemberId() {
    return memberId;
  }

  private void setMemberId(String memberId){
    this.memberId = memberId;
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  private void setEmail(String email) {
    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email format.");
    }
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  private void setPhoneNumber(String phoneNumber) {
    if (!isValidPhoneNumber(phoneNumber)) {
      throw new IllegalArgumentException("Invalid phone number format.");
    }
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  public int getCredits() {
    return credits;
  }

  private void setCreationDate(LocalDate creationDate){
    this.creationDate = creationDate;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public List<Item> getItemsOwned() {
    return itemsOwned;
  }

  public List<Contract> getLendingHistory() {
    return lendingHistory;
  }

  private void updateCredits(int amount) {
    this.credits += amount;
  }

  // Validation for email format
  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
  }

  // Validation for phone number format (basic numeric check)
  private boolean isValidPhoneNumber(String phoneNumber) {
    String phoneRegex = "\\d{10}"; // Assuming a 10-digit number for simplicity
    Pattern pattern = Pattern.compile(phoneRegex);
    return pattern.matcher(phoneNumber).matches();
  }

  // toString method for easy display of member information
  @Override
  public String toString() {
    return "Member ID: " + memberId + "\n" +
        "Name: " + name + "\n" +
        "Email: " + email + "\n" +
        "Phone: " + phoneNumber + "\n" +
        "Credits: " + credits + "\n" +
        "Creation Date: " + creationDate + "\n" +
        "Owned Items: " + itemsOwned.size() + "\n" +
        "Lending History: " + lendingHistory.size();
  }
}
