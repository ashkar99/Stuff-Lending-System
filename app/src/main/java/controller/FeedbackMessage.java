package controller;

/**
 * Enum class that holds all feedback messages a user can receive while using
 * the program.
 */
public enum FeedbackMessage {

  // Member related messages
  SUCCESS_MEMBER_CREATION("Member created successfully!"),
  SUCCESS_MEMBER_DELETION("Member deleted successfully!"),
  SUCCESS_MEMBER_UPDATE("Member information updated successfully!"),
  ERROR_DUPLICATE_EMAIL("Error: This email is already in use."),
  ERROR_DUPLICATE_PHONE_NUMBER("Error: This phone number is already in use."),
  ERROR_MEMBER_NOT_FOUND("Error: Member not found."),

  // Item related messages
  SUCCESS_ITEM_CREATION("Item created successfully! Member credited with 100 points."),
  SUCCESS_ITEM_DELETION("Item deleted successfully!"),
  SUCCESS_ITEM_UPDATE("Item information updated successfully!"),
  ERROR_ITEM_NOT_FOUND("Error: Item not found."),

  // Contract related messages
  SUCCESS_CONTRACT_CREATION("Contract established successfully!"),
  ERROR_INSUFFICIENT_FUNDS("Error: Borrower does not have enough credits."),
  ERROR_ITEM_UNAVAILABLE("Error: Item is not available during the specified period."),

  // Time related messages
  TIME_ADVANCED("Time advanced by %d days."),

  // General error messages
  ERROR_INVALID_INPUT("Error: Invalid input. Please try again."),
  ERROR_OPERATION_FAILED("Error: Operation failed. Please contact support."),

  // New general messages
  SUCCESS_OPERATION("Operation completed successfully!"),
  ERROR_FIELD_EMPTY("Error: One or more required fields are empty."),
  ERROR_NEGATIVE_VALUE("Error: Values cannot be negative."),

  // Contract specific messages
  ERROR_CONTRACT_TIME_CONFLICT("Error: The item is already booked for this time period."),
  ERROR_CONTRACT_INVALID_TIME("Error: Invalid start or end time for contract."),
  ERROR_INSUFFICIENT_CREDITS("Error: Not enough credits to complete the transaction."),
  SUCCESS_CONTRACT_TERMINATION("Contract terminated successfully.");

  private final String message;

  FeedbackMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the message associated with the enum value.
   *
   * @return The message as a String.
   */
  public String getMessage() {
    return message;
  }
}
