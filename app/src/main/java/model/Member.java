package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * The Member class represents a member in the system, who can lend and borrow
 * items.
 * It contains details such as name, email, phone number, password, credits, and
 * lists of owned items and lending history.
 */
public class Member {

  private String memberId;
  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private int credits = 0;
  private LocalDate creationDate;
  private List<Item> items = new ArrayList<>();
  private List<Contract> lendingHistory = new ArrayList<>();

  /**
   * Constructor to create a new Member with the provided details.
   * The email is used as the unique identifier (member ID).
   *
   * @param name        The name of the member.
   * @param email       The email address of the member (also used as the member
   *                    ID).
   * @param phoneNumber The phone number of the member.
   * @param password    The password for the member account.
   */
  public Member(String name, String email, String phoneNumber, String password) {
    setMemberId(email); // Use email as unique member id
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    setCreationDate();
  }

  /**
   * Gets the unique member ID, which is based on the email.
   *
   * @return The member ID.
   *
   */
  public String getMemberId() {
    return memberId;
  }

  /**
   * Sets the member ID. If the provided ID is null, a unique ID is generated.
   *
   * @param memberId The member ID to set.
   *
   */
  private void setMemberId(String memberId) {
    if (memberId == null) {
      this.memberId = generateUniqueMemberId();
    } else {
      this.memberId = memberId;
    }
  }

  /**
   * Gets the member's name.
   *
   * @return The member's name.
   *
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the member's name.
   *
   * @param name The name to set.
   *
   */
  private void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the member's email address.
   *
   * @return The member's email.
   *
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the member's email address. The email is validated before being set.
   *
   * @param email The email to set.
   *
   * @throws IllegalArgumentException if the email format is invalid.
   *
   */
  private void setEmail(String email) {
    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email format.");
    }
    this.email = email;
  }

  /**
   * Gets the member's phone number.
   *
   * @return The member's phone number.
   *
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the member's phone number. The phone number is validated before being
   * set.
   *
   * @param phoneNumber The phone number to set.
   *
   * @throws IllegalArgumentException if the phone number format is invalid.
   *
   */
  private void setPhoneNumber(String phoneNumber) {
    if (!isValidPhoneNumber(phoneNumber)) {
      throw new IllegalArgumentException("Invalid phone number format.");
    }
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the member's password.
   *
   * @return The member's password.
   *
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the member's password.
   *
   * @param password The password to set.
   *
   */
  private void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the number of credits the member has.
   *
   * @return The number of credits.
   *
   */
  public int getCredits() {
    return credits;
  }

  /**
   * Sets the member's creation date to the current day.
   */
  private void setCreationDate() {
    if (this.creationDate == null) {
      this.creationDate = LocalDate.now();
    }
  }

  /**
   * Gets the member's creation date.
   *
   * @return The creation date as an integer representing the day.
   *
   */
  public LocalDate getCreationDate() {
    return creationDate;
  }

  /**
   * Gets the list of items owned by the member.
   *
   * @return A list of items owned by the member.
   *
   */
  public List<Item> getItems() {
    return new ArrayList<>(items);
  }

  /**
   * Gets the list of contracts where the member lent items.
   *
   * @return A list of lending contracts.
   *
   */
  public List<Contract> getLendingHistory() {
    return new ArrayList<>(lendingHistory);
  }

  /**
   * Updates the number of credits the member has.
   *
   * @param amount The amount to add to the current credits.
   *
   */
  public void updateCredits(int amount) {
    this.credits += amount;
  }

  // Private Methods

  /**
   * Validates the email format using a regular expression.
   *
   * @param email The email to validate.
   *
   * @return True if the email format is valid, false otherwise.
   *
   */
  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
  }

  /**
   * Validates the phone number format using a regular expression.
   * The phone number is expected to be 10 digits.
   *
   * @param phoneNumber The phone number to validate.
   *
   * @return True if the phone number format is valid, false otherwise.
   *
   */
  private boolean isValidPhoneNumber(String phoneNumber) {
    String phoneRegex = "\\d{10}"; // Assuming a 10-digit number for simplicity
    Pattern pattern = Pattern.compile(phoneRegex);
    return pattern.matcher(phoneNumber).matches();
  }

  /**
   * Adding item to items.
   *
   * @param item 
   *
   */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
   * Removing item from items.
   *
   * @param item
   *
   */
  public void removeItem(Item item) {
    items.remove(item);
  }

  /**
   * Generates a unique member ID using a UUID and returns the first 5 characters.
   *
   * @return A unique member ID.
   *
   */
  private String generateUniqueMemberId() {
    String uuid = UUID.randomUUID().toString();
    return uuid.substring(0, 5); // Take the first 5 characters
  }

  /**
   *Finlizer.
   */
  @Override
  protected final void finalize() throws Throwable {
      // Tom finalizer för att förhindra attacker
  }

}

// @Override
// public String toString() {
// return "Member ID: " + memberId + "\n" +
// "Name: " + name + "\n" +
// "Email: " + email + "\n" +
// "Phone: " + phoneNumber + "\n" +
// "Credits: " + credits + "\n" +
// "Creation Date: " + creationDate + "\n" +
// "Owned Items: " + itemsOwned.size() + "\n" +
// "Lending History: " + lendingHistory.size();
// }
