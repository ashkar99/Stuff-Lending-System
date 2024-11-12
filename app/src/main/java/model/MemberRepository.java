package model;

import java.util.ArrayList;
import java.util.List;

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
  public void addMembers(Member member) {
    members.add(member);
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
}
