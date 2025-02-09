package controller;

import model.Member;
import model.SystemManager;
import view.FeedbackMessage;
import view.MemberViewer;

/**
 * Implementation of the {@link MemberDaoInterface}, responsible for managing
 * member-related operations such as adding, modifying, deleting, and retrieving
 * member information from an internal list.
 */
public class MemberDaoImpl implements MemberDaoInterface {
  private SystemManager systemManager = new SystemManager();
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
      systemManager.addMembers("Bob", "bob@example.com", "0987654321", "password");
      systemManager.addMembers("Alice", "alice@example.com", "2234567890", "password");
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void findbyList() {
    memberViewer.findMember(systemManager.getMembers());
  }

  @Override
  public void createMember() {
    String[] memberInfo = memberViewer.createMember();
    systemManager.addMembers(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3]);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_CREATION.getMessage(), null);
  }

  @Override
  public void updateMember() {
    findbyList();
    String[] memberInfo = memberViewer.editMemberInfo();
    systemManager.updateMember(memberInfo);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_UPDATE.getMessage(), null);

  }

  @Override
  public void deleteMember() {
    memberViewer.findMember(systemManager.getMembers());
    String[] memberInfo = memberViewer.deleteMember();
    systemManager.removeMember(memberInfo);
    memberViewer.displayFeedback(true, FeedbackMessage.SUCCESS_MEMBER_DELETION.getMessage(), null);
  }

  @Override
  public void showSpecificMemberInfo() {
    String memberId = memberViewer.specificMemberFullInfo(systemManager.getMembers());
    Member member = systemManager.getMemberById(memberId);
    memberViewer.displayMemberInfo(member);
  }

  @Override
  public void displayMembersOverview() {
    memberViewer.displayMembersOverview(systemManager.getMembers());
  }

  @Override
  public void displayMembersWithDetailedItems() {
    memberViewer.displayMembersWithDetailedItems(systemManager.getMembers());
  }

  @Override
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}
