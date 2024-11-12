package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Member class represents a member in the system, who can lend and borrow
 * items.
 * It contains details such as name, email, phone number, password, credits, and
 * lists of owned items and lending history.
 */
public class Member extends FatherOfFunction {

  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private int credits = 0;
  private List<Item> items = new ArrayList<>();
  private List<ImmutableContract> lendingHistory = new ArrayList<>();

  /**
   * Constructor to create a new Member with the provided details.
   *
   * @param name        The name of the member.
   * @param email       The email address of the member
   * @param phoneNumber The phone number of the member.
   * @param password    The password for the member account.
   */
  public Member(String name, String email, String password, String phoneNumber) {
    setId();
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    setCreationDate();
  }

  /**
   * Copy constructor to create a deep copy.
   *
   * @param member to return a copy of it.
   */
  public Member(Member member) {
    this.id = member.id;
    this.name = member.name;
    this.email = member.email;
    this.phoneNumber = member.phoneNumber;
    this.password = member.password;
    this.credits = member.credits;
    this.items = new ArrayList<>(member.items); // Deep copy of items
  }

  /**
   * Update member informations.
   *
   * @param name        Update name.
   * @param email       Update email.
   * @param phoneNumber Update phone number.
   * @param password    Update password.
   *
   */
  public void updateMember(String name, String email, String password, String phoneNumber) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
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
  public List<ImmutableContract> getLendingHistory() {
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
   * Adding the contract to lendingHistory.
   *
   * @param contract Add contract.
   */
  public void addContract(ImmutableContract contract) {
    lendingHistory.add(contract);
  }
}
