package model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Member {
  private String memberId;
  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private int credits = 0;
  private int creationDate;
  private List<Item> itemsOwned = new ArrayList<>();
  private List<Contract> lendingHistory = new ArrayList<>();

  public Member(String name, String email, String phoneNumber, String password) {
    setMemberId(email); //Use email as unique member id
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    setCreationDate();
  }

  public String getMemberId() {
    return memberId;
  }

  private void setMemberId(String memberId) {
    if (memberId == null) {
      this.memberId = generateUniqueMemberId();
    } else {
      this.memberId = memberId;
    }
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

  private void setCreationDate() {
    Time time = new Time();
      this.creationDate = time.getCurrentDay();

  }

  public int getCreationDate() {

    return creationDate;
  }

  public List<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public List<Contract> getLendingHistory() {
    return lendingHistory;
  }

  public void updateCredits(int amount) {
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

  private String generateUniqueMemberId() {
    String uuid = UUID.randomUUID().toString();
    return uuid.substring(0, 5); // Take the first 6 characters
  }

  @Override
  public String toString() {
    return "Member ID: " + memberId + "\n" +
        "Name: " + name + "\n" +
        "Email: " + email + "\n" +
        "Phone: " + phoneNumber + "\n" +
        "Credits: " + credits + "\n" +
        "Creation Date: " + creationDate + "\n" +
        "Owned Items: " + items.size() + "\n" +
        "Lending History: " + lendingHistory.size();
  }
}
