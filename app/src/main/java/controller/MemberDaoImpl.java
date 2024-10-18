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
    for (Member member : members) {
      if (member.getEmail().equals(email)) {
        throw new IllegalArgumentException("The email is already in use");
      }
      if (member.getPhoneNumber().equals(phoneNumber)) {
        throw new IllegalArgumentException("The phone number is already in use");
      }
    }
    Member newMember = new Member(name, email, phoneNumber, password, creationDate);
    members.add(newMember);
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
  public void modifyMember(String memberId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modifyMember'");
  }

  @Override
  public void showSpecificMember(String memberId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'showSpecificMember'");
  }

  @Override
  public void listSimpleMembersInfo() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listSimpleMembersInfo'");
  }

  @Override
  public void listAllMembersInfo() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listAllMembersInfo'");
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
