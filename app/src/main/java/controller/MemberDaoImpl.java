package controller;

import java.util.ArrayList;
import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * Implementation of the {@link MemberDaoInterface}, responsible for managing
 * member-related operations such as adding, modifying, deleting, and retrieving
 * member information from an internal list.
 */
public class MemberDaoImpl implements MemberDaoInterface {

  private List<Member> members = new ArrayList<>();

  /**
   * Constructor for the MemberDaoImpl class.
   */
  public MemberDaoImpl() {
    generated();
  }

  /**
   * Generates initial data for testing purposes.
   */
  private void generated() {
    try {
      Member bob = new Member("Bob", "bob@example.com", "0987654321", "password");
      members.add(bob);
      Member alice = new Member("Alice", "alice@example.com", "1234567890", "password");
      members.add(alice);
      alice.updateCredits(40);
      addMember("Charlie", "charlie@example.com", "1122334455", "password");

      // Add Items to members
      Item item = new Item(CategoryEnum.TOOL, "Hammer", "Steel hammer", 10, bob);
      bob.addItem(item);
      bob.addItem(new Item(CategoryEnum.GAME, "Monopoly game", "Board Game", 2, bob));
      bob.addItem(new Item(CategoryEnum.TOY, "Toy car", "Red remote control car", 20, bob));
      bob.addItem(new Item(CategoryEnum.SPORT, "Tennis Racket", "Wilson Pro racket", 0, bob));

      // Create a contract
      ContractDaoInterface contract = new ContractDaoImpl();
      contract.createContract(bob, alice, item, 1, 3);

    } catch (Exception e) {
      throw new RuntimeException("Error generating initial data: " + e.getMessage(), e);
    }
  }

  /**
   * Adds a new member to the list after checking for unique email and phone
   * number.
   */
  @Override
  public void addMember(String name, String email, String phoneNumber, String password) {
    try {
      checkUnique(email, phoneNumber);
      Member newMember = new Member(name, email, phoneNumber, password);
      members.add(newMember);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error adding member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while adding the member.", e);
    }
  }

  /**
   * Modifies the details of an existing member, based on their ID.
   * If a field is null or blank, the current value will be kept.
   *
   * @param memberId    The ID of the member to be modified.
   * @param name        The new name for the member.
   * @param email       The new email for the member.
   * @param phoneNumber The new phone number for the member.
   * @param password    The new password for the member.
   * @throws IllegalArgumentException if the member is not found.
   */
  @Override
  public void modifyMember(String memberId, String name, String email, String phoneNumber, String password) {
    try {
      Member member = getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      // Update member details
      String newName = (name != null && !name.isBlank()) ? name : member.getName();
      String newEmail = (email != null && !email.isBlank()) ? email : member.getEmail();
      String newPhoneNumber = (phoneNumber != null && !phoneNumber.isBlank()) ? phoneNumber : member.getPhoneNumber();
      String newPassword = (password != null && !password.isBlank()) ? password : member.getPassword();

      member.updateMember(newName, newEmail, newPhoneNumber, newPassword);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error modifying member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while modifying the member.", e);
    }
  }

  /**
   * Deletes a member from the list based on their ID and password.
   *
   * @param memberId The ID of the member to be deleted.
   * @param password The password of the member.
   * @throws IllegalArgumentException if the member is not found or the password
   *                                  is incorrect.
   */
  @Override
  public void deleteMember(String memberId, String password) {
    try {
      Member member = getMemberById(memberId);
      if (member != null && member.getPassword().equals(password)) {
        members.remove(member);
      } else {
        throw new IllegalArgumentException("Member not found or password is incorrect!");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error deleting member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while deleting the member.", e);
    }
  }

  /**
   * Checks if the provided email or phone number is already in use by another
   * member.
   *
   * @param email       The email to check.
   * @param phoneNumber The phone number to check.
   * @throws IllegalArgumentException if the email or phone number is already in
   *                                  use.
   */
  public void checkUnique(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email)) {
        throw new IllegalArgumentException("The email is already in use.");
      }
      if (member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException("The phone number is already in use.");
      }
    }
  }

  /**
   * Returns a deep copy of the member's information based on their ID.
   *
   * @param memberId The ID of the member to retrieve.
   * @return A {@link Member} object containing the member's details.
   * @throws IllegalArgumentException if the member is not found.
   */
  @Override
  public Member showSpecificMemberInfo(String memberId) {
    try {
      Member member = getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }
      // Return a deep copy of the member object to avoid exposing the original object
      return new Member(member);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error retrieving member information: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while retrieving the member information.", e);
    }
  }

  /**
   * Finds a member by their ID.
   *
   * @param memberId The ID of the member to search for.
   * @return The {@link Member} object if found, or null if not found.
   */
  @Override
  public Member getMemberById(String memberId) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Retrieves the full list of members.
   *
   * @return A list of {@link Member} objects representing all members.
   */
  @Override
  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }

  /**
   * Finalizer.
   */
  @Override
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}
