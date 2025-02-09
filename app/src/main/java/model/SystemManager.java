package model;

import java.util.ArrayList;
import java.util.List;

import view.FeedbackMessage;

/**
 * The {@code systemManager} class provides storage and management of
 * members.
 * It supports adding, removing, and retrieving members.
 */
public class SystemManager {
  private List<Member> members = new ArrayList<>();
  private Time time = new Time(0);

  /**
   * Constructs an empty {@code systemManager}.
   */
  public SystemManager() {
  }

  /**
   * Adds a new member to the repository.
   *
   * @param member the {@code Member} to add
   */
  public void addMembers(String name, String email, String password, String phoneNumber) {
    Member newMember = new Member(name, email, password, phoneNumber);
    validateMemberDetails(name, email, password, phoneNumber);
    members.add(newMember);
  }

  /**
   * Update the details of an existing member.
   *
   * @param memberInfo to be replaced.
   */
  public void updateMember(String[] memberInfo) {
    Member member = getMemberById(memberInfo[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    String newName = !memberInfo[1].isBlank() ? memberInfo[1] : member.getName();
    String newEmail = !memberInfo[2].isBlank() ? memberInfo[2] : member.getEmail();
    String newPassword = !memberInfo[3].isBlank() ? memberInfo[3] : member.getPassword();
    String newPhoneNumber = !memberInfo[4].isBlank() ? memberInfo[4] : member.getPhoneNumber();

    member.updateMember(newName, newEmail, newPassword, newPhoneNumber);
  }

  /**
   * Removes a member from the repository.
   *
   * @param member the {@code Member} to remove
   */
  public void removeMember(String[] memberInfo) {
    Member member = getMemberById(memberInfo[0]);
    if (member != null && member.getPassword().equals(memberInfo[1])) {
      members.remove(member);
    } else {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

  }

  /**
   * Returns a list of all members in the repository.
   * The returned list is a copy to prevent external modifications to the
   * repository.
   *
   * @return a {@code List} of {@code Member} objects
   */
  public List<Member> getMembers() {
    if (members.isEmpty()) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_NO_MEMBERS_TO_DISPLAY.getMessage());
    } else {
      return new ArrayList<>(members);
    }
  }

  /**
   * Finds a member by their unique ID.
   *
   * @param memberId The ID of the member to search for.
   * @return The {@link Member} object if found, or null if not found.
   */
  public Member getMemberById(String memberId) {
    for (Member member : getMembers()) {
      if (member.getId().equals(memberId)) {
        return member;
      }
    }
    throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
  }

  public void createItem(String[] itemInfo) {
    if (itemInfo[1].isBlank() || itemInfo[2].isBlank() || itemInfo[3].isBlank()
        || Integer.parseInt(itemInfo[4]) < 0) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }
    Item newItem = new Item(CategoryEnum.valueOf(itemInfo[1]), itemInfo[2], itemInfo[3],
        Integer.parseInt(itemInfo[4]), getMemberById(itemInfo[0]));
    getMemberById(itemInfo[0]).addItem(newItem);
    getMemberById(itemInfo[0]).updateCredits(100);

  }

  public void updateItem(String[] itemInfo) {
    Item item = getItemById(getMemberById(itemInfo[0]), itemInfo[1]);
    String newName = !itemInfo[3].isBlank() ? itemInfo[3] : item.getName();
    String newDescription = !itemInfo[4].isBlank() ? itemInfo[4] : item.getDescription();
    CategoryEnum newCategory;
    newCategory = CategoryEnum.safeCategoryParse(itemInfo[2], item.getCategory());
    int newCostPerDay = (!itemInfo[5].isBlank() && Integer.parseInt(itemInfo[5]) > 0)
        ? Integer.parseInt(itemInfo[5])
        : item.getCostPerDay();
    Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, getMemberById(itemInfo[0]));
    getMemberById(itemInfo[0]).removeItem(item);
    getMemberById(itemInfo[0]).addItem(updatedItem);
  }

  public void deleteItem(String[] itemInfo) {
    getMemberById(itemInfo[0]).removeItem(getItemById(getMemberById(itemInfo[0]), itemInfo[1]));
  }

  /**
   * Get item by id.
   *
   * @param member to get member's item.
   * @param itemId to get the item.
   * @return item by the id.
   */
  public Item getItemById(Member member, String itemId) {
    for (Item item : member.getItems()) {
      if (item.getId().equals(itemId)) {
        return item;
      }
    }
    throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
  }

  /**
   * Return a list of available items. TODO not used method.
   */
  public List<Item> getAvailableItems() {
    List<Item> avItems = new ArrayList<>();
    for (Member member : getMembers()) {
      for (Item item : member.getItems()) {
        if (item.isAvailable()) {
          avItems.add(item);
        }
      }
    }
    return new ArrayList<>(avItems);
  }

  public void createContract(String[] contractInfo) {
    Member lender = getMemberById(contractInfo[0]);
    Member borrower = getMemberById(contractInfo[1]);

    // Fetch the item after verifying that the lender is not null
    Item item = getItemById(lender, contractInfo[2]);

    int startDay = Integer.parseInt(contractInfo[3]);
    int endDay = Integer.parseInt(contractInfo[4]);

    int currentDay = time.getCurrentDay();

    // Check if the start day is in the past
    if (startDay < currentDay) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_CONTRACT_INVALID_TIME.getMessage());
    }

    // Check if the item is available for the given time period
    if (!item.isAvailableForPeriod(startDay, endDay)) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_UNAVAILABLE.getMessage());
    }

    // Check if the borrower has enough funds
    if (!isEnoughFundsToBorrow(borrower, item, startDay, endDay)) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
    }

    // Create the contract and update the item and lender
    ImmutableContract newContract = new ImmutableContract(lender, borrower, item, startDay, endDay);
    borrower.updateCredits(-item.getCostPerDay() * (endDay - startDay + 1));
    lender.updateCredits(item.getCostPerDay() * (endDay - startDay + 1));
    item.addContract(newContract);
    lender.addContract(newContract);
  }

  public boolean isEnoughFundsToBorrow(Member borrower, Item item, int startDay, int endDay) {
    int borrowerFunds = borrower.getCredits();
    int itemTotalCost = item.getCostPerDay() * (endDay - startDay + 1);
    if (borrowerFunds < itemTotalCost) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_INSUFFICIENT_CREDITS.getMessage());
    }
    return true;
  }

  /**
   * Validates member details by ensuring that no fields are empty and that
   * the email and phone number are unique.
   *
   * @param name        The member's name (must not be blank).
   * @param email       The member's email (must be unique and not blank).
   * @param password    The member's password (must not be blank).
   * @param phoneNumber The member's phone number (must be unique and not blank).
   * @throws IllegalArgumentException If any field is blank or if the email/phone
   *                                  number already exists.
   */
  public void validateMemberDetails(String name, String email, String password, String phoneNumber) {
    if (name.isBlank() || email.isBlank() || password.isBlank() || phoneNumber.isBlank()) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }
    checkUnique(email, phoneNumber);
  }

  /**
   * Checks whether the provided email or phone number is already in use by
   * another member.
   *
   * @param email       The email to check for uniqueness.
   * @param phoneNumber The phone number to check for uniqueness.
   * @throws IllegalArgumentException If the email or phone number already exists.
   */
  private void checkUnique(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_EXISTS.getMessage());
      }
    }
  }
}
