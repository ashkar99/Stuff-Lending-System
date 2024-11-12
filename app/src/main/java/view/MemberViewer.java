package view;

import controller.FeedbackMessage;
import java.util.List;
import model.Member;
import model.MemberRepository;

/**
 * Handles member-related actions like creating, editing, deleting members, and
 * displaying member information.
 */
public class MemberViewer extends BaseViewer {

  private final ItemViewer itemViewer;

  public MemberViewer() {
    this.itemViewer = new ItemViewer();
  }

  /**
   * Allows a new user to sign in by providing their personal details: name,
   * email, password, and phone number. The method adds the new member to the
   * system.
   */
  public String[] createMember() {
    String name = promptForInput("Enter your name: ");
    String email = promptForInput("Enter your email: ");
    String password = promptForInput("Enter your password: ");
    String phoneNumber = promptForInput("Enter your phone number: ");
    String [] member = {name,email,password,phoneNumber};
    System.out.println(FeedbackMessage.SUCCESS_MEMBER_CREATION.getMessage());
    waitForUserInput();
    return member;
  }

  /**
   * Allows the user to edit member information such as name, email, phone number,
   * or password.
   */
  public String[] editMemberInfo() {
    String memberId = promptForInput("Enter member ID: ");
    String name = promptForInput("Enter new name: ");
    String email = promptForInput("Enter new email: ");
    String password = promptForInput("Enter new password: ");
    String phoneNumber = promptForInput("Enter new phone number: ");
    String [] member = {memberId,name,email,password,phoneNumber};

    System.out.println(FeedbackMessage.SUCCESS_MEMBER_UPDATE.getMessage());
    waitForUserInput();
    return member;
  }

  /**
   * Allows the user to delete a member based on the provided member ID and
   * password.
   */
  public String[] deleteMember() {
    String memberId = promptForInput("Enter member ID: ");
    String password = promptForInput("Enter password: ");
    String [] member = {memberId,password};
    System.out.println(FeedbackMessage.SUCCESS_MEMBER_DELETION.getMessage());
    waitForUserInput();
    return member;
  }

  /**
   * Displays detailed information about a specific member based on the member ID.
   */
  public String specificMemberFullInfo(List<Member> members) {
    displayMembersOverview(members);
    String memberId = promptForInput("Enter the member ID to view full information: ");

 
      //System.out.println(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    
    waitForUserInput();
    return memberId;
  }

  /**
   * Displays a simple list of all members, showing only basic information like
   * name, email, and phone number.
   */
  public void displayMembersOverview(List<Member> members) {
    if (members.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_MEMBERS_TO_DISPLAY.getMessage());
    } else {
      for (Member member : members) {
        System.out.println("----------------------------------------");
        System.out.println("Member ID: " + member.getId());
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Credits: " + member.getCredits());
        System.out.println("Owned items: " + member.getItems().size());
        System.out.println("----------------------------------------");
      }
    }
  }

  /**
   * Displays all members along with their detailed items.
   */
  public void displayMembersWithDetailedItems(List<Member> members) {
    if (members.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_MEMBERS_TO_DISPLAY.getMessage());
    } else {
      for (Member member : members) {
        System.out.println("----------------------------------------");
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        itemViewer.viewItems(member);
        System.out.println("----------------------------------------");
      }
    }
  }

  /**
   * Utility method to display full member information.
   *
   * @param member The member object to display.
   */
  private void displayMemberInfo(Member member) {
    System.out.println("----------------------------------------");
    System.out.println("Member ID: " + member.getId());
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
    System.out.println("Owned items: " + member.getItems().size());
    System.out.println("----------------------------------------");
  }
}
