package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that store and handle the members.
 */
public class MemberRepository {
  private List<Member> members = new ArrayList<>();

  public MemberRepository() {

  }

  public void addMembers(Member member) {
    members.add(member);
  }

  public void removeMember(Member member) {
    members.remove(member);
  }

  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }
}
