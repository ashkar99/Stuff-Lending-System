package controller;

import java.util.ArrayList;
import java.util.List;
import model.Member;

/**
 * Implementation of the {link MemberDaoInterface}, responsible for managing
 * member-related operations such as adding, modifying, deleting, and retrieving
 * member information from an internal list.
 */
public class MemberDaoImpl implements MemberDaoInterface {

  private List<Member> members = new ArrayList<>();

  /**
   * Constructor for the MemberDaoImpl class. Initializes an empty list of
   * members.
   */
  public MemberDaoImpl() {
    // Constructor
  }

  /**
   * Adds a new member to the list after checking for unique email and phone
   * number.
   */
  @Override
  public void addMember(String name, String email, String phoneNumber, String password) {
    checkUnique(email, phoneNumber);
    Member newMember = new Member(name, email, phoneNumber, password);
    members.add(newMember);
  }

  /**
   * Modifies the details of an existing member, based on their ID.
   * If a field is null or blank, the current value will be kept.
   *
   * @param memberId    The ID of the member to be modified.
   *
   * @param name        The new name for the member.
   *
   * @param email       The new email for the member.
   *
   * @param phoneNumber The new phone number for the member.
   *
   * @param password    The new password for the member.
   *
   * @throws IllegalArgumentException if the member is not found.
   *
   */
  @Override
  public void modifyMember(String memberId, String name, String email, String phoneNumber, String password) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }
    // Update member details
    String newName = (name != null && !name.isBlank()) ? name : member.getName();
    String newEmail = (email != null && !email.isBlank()) ? email : member.getEmail();
    String newPhoneNumber = (phoneNumber != null && !phoneNumber.isBlank()) ? phoneNumber : member.getPhoneNumber();
    String newPassword = (password != null && !password.isBlank()) ? password : member.getPassword();

    Member updatedMember = new Member(newName, newEmail, newPhoneNumber, newPassword);

    // Replace the old member with the updated one
    members.remove(member);
    members.add(updatedMember);
  }

  /**
   * Checks if the provided email or phone number is already in use by another
   * member.
   *
   * @param email       The email to check.
   *
   * @param phoneNumber The phone number to check.
   *
   * @throws IllegalArgumentException if the email or phone number is already in
   *                                  use.
   */
  public void checkUnique(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email)) {
        throw new IllegalArgumentException("The email is already in use");
      }
      if (member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException("The phone number is already in use");
      }
    }
  }

  /**
   * Deletes a member from the list based on their ID and password.
   *
   * @param memberId The ID of the member to be deleted.
   * @param password The password of the member.
   * @throws IllegalArgumentException if the member is not found or the password
   *                                  is incorrect.
   *
   */
  @Override
  public void deleteMember(String memberId, String password) {
    Member member = findMemberById(memberId);
    if (member != null && !member.getPassword().equals(password)) {
      members.remove(member);
    } else {
      throw new IllegalArgumentException("Member not found or password is incorrect!");
    }
  }

  /**
   * Returns detailed information of a specific member based on their ID.
   *
   * @param memberId The ID of the member to retrieve.
   * @return A {@link Member} object containing the member's details.
   * @throws IllegalArgumentException if the member is not found.
   *
   */
  @Override
  public Member showSpecificMemberInfo(String memberId) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }
    // Return a copy of the member data
    return new Member(
        member.getName(),
        member.getEmail(),
        member.getPhoneNumber(),
        member.getPassword());
  }

  /**
   * Lists basic information (name, email, phone number) of all members.
   *
   * @return A list of {@link Member} objects containing basic member information.
   *
   */
  @Override
  public List<Member> listSimpleMembersInfo() {
    List<Member> simpleMembersList = new ArrayList<>();
    for (Member member : members) {
      simpleMembersList.add(new Member(
          member.getName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getPassword()));
    }
    return simpleMembersList;
  }

  /**
   * Lists all available information of every member.
   *
   * @return A list of {@link Member} objects containing full member information.
   *
   */
  @Override
  public List<Member> listAllMembersInfo() {
    List<Member> verboseMembersList = new ArrayList<>();
    for (Member member : members) {
      verboseMembersList.add(new Member(
          member.getName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getPassword()));
    }
    return verboseMembersList;
  }

  /**
   * Finds a member by their ID.
   *
   * @param memberId The ID of the member to search for.
   *
   * @return The {@link Member} object if found, or null if not found.
   *
   */
  @Override
  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Retrieves the full list of members.
   *
   * @return A list of {@link Member} objects representing all members.
   *
   */
  @Override
  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }
}
