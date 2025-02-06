package model;

import java.util.ArrayList;
import java.util.List;

import view.FeedbackMessage;

/**
 * The {@code MemberRepository} class provides storage and management of
 * members.
 * It supports adding, removing, and retrieving members.
 */
public class MemberRepository {
  private List<Member> members = new ArrayList<>();

  /**
   * Constructs an empty {@code MemberRepository}.
   */
  public MemberRepository() {
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
  public void removeMember(Member member) {
    members.remove(member);
  }

  /**
   * Returns a list of all members in the repository.
   * The returned list is a copy to prevent external modifications to the
   * repository.
   *
   * @return a {@code List} of {@code Member} objects
   */
  public List<Member> getMembers() {
    return new ArrayList<>(members);
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
    return null;
  }

  public void validateMemberDetails(String name, String email, String password, String phoneNumber) {
    if (name.isBlank() || email.isBlank() || password.isBlank() || phoneNumber.isBlank()) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }
    checkUnique(email, phoneNumber);
  }

  private void checkUnique(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_EXISTS.getMessage());
      }
    }
  }

}
