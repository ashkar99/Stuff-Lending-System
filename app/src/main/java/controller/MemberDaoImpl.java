package controller;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Member;
import model.MemberRepository;
import view.FeedbackMessage;
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
   * TODO
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
  public void findbyList() {
    memberViewer.findMember(memberRepository.getMembers());
  }

  @Override
  public void createMember() {
    String[] memberInfo = memberViewer.createMember();
    checkUnique(memberInfo[1], memberInfo[3]);
    if (memberInfo[0].isBlank() || memberInfo[1].isBlank() || memberInfo[2].isBlank() || memberInfo[3].isBlank()) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }

    Member newMember = new Member(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3]);
    memberRepository.addMembers(newMember);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_CREATION.getMessage(), null);
  }

  @Override
  public void modifyMember() {
    findbyList();
    String[] memberInfo = memberViewer.editMemberInfo();
    Member member = getMemberById(memberInfo[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    String newName = !memberInfo[1].isBlank() ? memberInfo[1] : member.getName();
    String newEmail = !memberInfo[2].isBlank() ? memberInfo[2] : member.getEmail();
    String newPassword = !memberInfo[3].isBlank() ? memberInfo[3] : member.getPassword();
    String newPhoneNumber = !memberInfo[4].isBlank() ? memberInfo[4] : member.getPhoneNumber();

    member.updateMember(newName, newEmail, newPassword, newPhoneNumber);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_UPDATE.getMessage(), null);

  }

  @Override
  public void deleteMember() {
    memberViewer.findMember(memberRepository.getMembers());
    String[] memberInfo = memberViewer.deleteMember();

    Member member = getMemberById(memberInfo[0]);
    if (member != null && member.getPassword().equals(memberInfo[1])) {
      memberRepository.removeMember(member);
      memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_DELETION.getMessage(), null);
    } else {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
  }

  @Override
  public void showSpecificMemberInfo() {
    String memberId = memberViewer.specificMemberFullInfo(memberRepository.getMembers());
    Member member = getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    memberViewer.displayMemberInfo(member);
  }

  @Override
  public void displayMembersOverview() {
    memberViewer.displayMembersOverview(memberRepository.getMembers());
  }

  @Override
  public void displayMembersWithDetailedItems() {
    memberViewer.displayMembersWithDetailedItems(memberRepository.getMembers());
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
