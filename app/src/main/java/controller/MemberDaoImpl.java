package controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import model.Member;

public class MemberDaoImpl implements MemberDaoInterface {
  private List<Member> members = new ArrayList<>();

  public MemberDaoImpl() {
    // Constructor
  }

  @Override
  public void addMember(String name, String email, String phoneNumber, String password, LocalDate creationDate) {
    checkUnique(email, phoneNumber);
    Member newMember = new Member(name, email, phoneNumber, password, creationDate);
    members.add(newMember);
  }

  @Override
  public void modifyMember(String memberId, String name, String email, String phoneNumber, String password) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }
    // Create a new member instance with updated information
    // If name is valid -> assign as the new name otherwise keep the old name
    String newName = (name != null && !name.isBlank()) ? name : member.getName();
    String newEmail = (email != null && !email.isBlank()) ? email : member.getEmail();
    String newPhoneNumber = (phoneNumber != null && !phoneNumber.isBlank()) ? phoneNumber : member.getPhoneNumber();
    String newPassword = (password != null && !password.isBlank()) ? password : member.getPassword();
    LocalDate creationDate = member.getCreationDate(); // Keep original creation date

    Member updatedMember = new Member(newName, newEmail, newPhoneNumber, newPassword, creationDate);

    // Remove the old member and add the updated member
    members.remove(member);
    members.add(updatedMember);
  }

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

  @Override
  public void deleteMember(String memberId, String password) {
    Member member = findMemberById(memberId);
    if (member != null && !member.getPassword().equals(password)) {
      members.remove(member);
    } else {
      throw new IllegalArgumentException("Member not found or password is incorrect!");
    }
  }

  @Override
  public Member showSpecificMemberInfo(String memberId) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }
    // Return a copy of the member data to the view
    return new Member(
        member.getName(),
        member.getEmail(),
        member.getPhoneNumber(),
        member.getPassword(),
        member.getCreationDate());
  }

  @Override
  public List<Member> listSimpleMembersInfo() {
    List<Member> simpleMembersList = new ArrayList<>();
    for (Member member : members) {
      // Return simple date in a list
      simpleMembersList.add(new Member(
          member.getName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getPassword(),
          member.getCreationDate()));
    }
    return simpleMembersList;
  }

  @Override
  public List<Member> listAllMembersInfo() {
    List<Member> verboseMembersList = new ArrayList<>();
    for (Member member : members) {
      // Return the full member data for verbose info
      verboseMembersList.add(new Member(
          member.getName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getPassword(),
          member.getCreationDate()));
    }
    return verboseMembersList;
  }

  @Override
  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  @Override
  public List<Member> getMembers() {
    return members;
  }
}
