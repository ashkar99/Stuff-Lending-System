package controller;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Member;
import model.MemberRepository;
import view.MemberViewer;

/**
 * Implementation of the {@link MemberDaoInterface}, responsible for managing
 * member-related operations such as adding, modifying, deleting, and retrieving
 * member information from an internal list.
 */
public class MemberDaoImpl implements MemberDaoInterface {
  private MemberRepository memberRepository = new MemberRepository();
  private MemberViewer memberViewer = new MemberViewer();

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
      memberRepository.addMembers(bob);
      Member alice = new Member("Alice", "alice@example.com", "2234567890", "password");
      memberRepository.addMembers(alice);
      alice.updateCredits(40);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  

  @Override
  public void createMember() {
    String[] memberInfo = memberViewer.createMember();
    try {
      checkUnique(memberInfo[1], memberInfo[3]);
      if (memberInfo[0].isBlank() || memberInfo[1].isBlank() || memberInfo[2].isBlank() || memberInfo[3].isBlank()) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }
      Member newMember = new Member(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3]);
      memberRepository.addMembers(newMember);

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
  public void modifyMember() {
    String[] memberInfo = memberViewer.editMemberInfo();
    try {
      Member member = getMemberById(memberInfo[0]);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      // Validate fields (removed null checks since the values are expected to be
      // non-null)
      if (memberInfo[1].isBlank() || memberInfo[2].isBlank() || memberInfo[3].isBlank() || memberInfo[4].isBlank()) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }

      String newName = !memberInfo[1].isBlank() ? memberInfo[1] : member.getName();
      String newEmail = !memberInfo[2].isBlank() ? memberInfo[2] : member.getEmail();
      String newPassword = !memberInfo[3].isBlank() ? memberInfo[3] : member.getPassword();
      String newPhoneNumber = !memberInfo[4].isBlank() ? memberInfo[4] : member.getPhoneNumber();

      member.updateMember(newName, newEmail, newPassword, newPhoneNumber);

      // Member successfully updated, message can be handled in the view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error modifying member: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void deleteMember() {
    String[] memberInfo = memberViewer.deleteMember();
    try {
      Member member = getMemberById(memberInfo[0]);
      if (member != null && member.getPassword().equals(memberInfo[1])) {
        memberRepository.removeMember(member);
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
    for (Member member : memberRepository.getMembers()) {
      if (member.getId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }
  /**
   * Return a list of available items.
   */
  @Override
  public List<Item> getAvailableItems() {
    List<Item> avItems = new ArrayList<>();
    for (Member member : memberRepository.getMembers()) {
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
    for (Member member : memberRepository.getMembers()) {
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
