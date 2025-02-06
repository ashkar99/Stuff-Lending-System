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
      memberRepository.addMembers("Bob", "bob@example.com", "0987654321", "password");
      memberRepository.addMembers("Alice", "alice@example.com", "2234567890", "password");
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
    memberRepository.addMembers(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3]);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_CREATION.getMessage(), null);
  }

  @Override
  public void updateMember() {
    findbyList();
    String[] memberInfo = memberViewer.editMemberInfo();
    memberRepository.updateMember(memberInfo);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_UPDATE.getMessage(), null);

  }

  @Override
  public void deleteMember() {
    memberViewer.findMember(memberRepository.getMembers());
    String[] memberInfo = memberViewer.deleteMember();
    memberRepository.removeMember(memberInfo);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_DELETION.getMessage(), null);
  }

  @Override
  public void showSpecificMemberInfo() {
    String memberId = memberViewer.specificMemberFullInfo(memberRepository.getMembers());
    Member member = memberRepository.getMemberById(memberId);
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
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}
