package controller;

import java.util.ArrayList;
import java.util.List;
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
  }

  /**
   * Generates initial data for testing purposes.
   */
  public void generated() {
    try {
      Member bob = new Member("Bob", "bob@example.com", "0987654321", "password");
      members.add(bob);
      Member alice = new Member("Alice", "alice@example.com", "2234567890", "password");
      members.add(alice);
      alice.updateCredits(40);
      addMember("Charlie", "charlie@example.com", "1122334455", "password");
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  public void addMembers(Member member) {
    members.add(member);
  }

  @Override
  public void addMember(String name, String email, String phoneNumber, String password) {
    try {
      checkUnique(email, phoneNumber);
      if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank()) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }
      Member newMember = new Member(name, email, phoneNumber, password);
      members.add(newMember);
    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("email")) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_DUPLICATE_EMAIL.getMessage(), e);
      } 
      if (e.getMessage().contains("phone number")) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_DUPLICATE_PHONE_NUMBER.getMessage(), e);
      }
      throw new IllegalArgumentException("Error adding member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void modifyMember(String memberId, String name, String email, String phoneNumber, String password) {
    try {
      Member member = getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      // Validate fields (removed null checks since the values are expected to be
      // non-null)
      if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank()) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }

      String newName = !name.isBlank() ? name : member.getName();
      String newEmail = !email.isBlank() ? email : member.getEmail();
      String newPhoneNumber = !phoneNumber.isBlank() ? phoneNumber : member.getPhoneNumber();
      String newPassword = !password.isBlank() ? password : member.getPassword();

      member.updateMember(newName, newEmail, newPhoneNumber, newPassword);

      // Member successfully updated, message can be handled in the view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error modifying member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void deleteMember(String memberId, String password) {
    try {
      Member member = getMemberById(memberId);
      if (member != null && member.getPassword().equals(password)) {
        members.remove(member);
        // Member successfully deleted, message can be handled in the view.
      } else {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error deleting member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public Member showSpecificMemberInfo(String memberId) {
    try {
      Member member = getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }
      return new Member(member); // Return a copy of the member
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public Member getMemberById(String memberId) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  @Override
  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }

  /**
   * Return a list of available items.
   */
  @Override
  public List<Item> getAvilbaleItems() {
    List<Item> avItems = new ArrayList<>();
    for (Member member : members) {
      for (Item item : member.getItems()) {
        if (item.isAvailable()) {
          avItems.add(item);
        }

      }
    }
    return new ArrayList<>(avItems);
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
        throw new IllegalArgumentException(FeedbackMessage.ERROR_DUPLICATE_EMAIL.getMessage());
      }
      if (member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_DUPLICATE_PHONE_NUMBER.getMessage());
      }
    }
  }

  @Override
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}
