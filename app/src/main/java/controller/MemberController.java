package controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import model.Member;

public class MemberController {
  private List<Member> members = new ArrayList<>();

  public MemberController() {

  }

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

  public void deleteMember(String memberId, String password) {
    Member member = findMemberById(memberId);
    if (member != null && member.getPassword().equals(password)) {
      members.remove(member);
    } else{
      throw new IllegalArgumentException("Member not found or password is incorrect!");
    }
  }

  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId))
        return member;
    }
    return null;
  }

  public List<Member> getMembers() {
    return members;
  }
}
