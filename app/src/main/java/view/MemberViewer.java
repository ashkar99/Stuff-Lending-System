package view;

import controller.FeedbackMessage;
import controller.MemberDaoInterface;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Member;

/**
 * Handles member-related actions like creating, editing, deleting members, and displaying member information.
 */
public class MemberViewer {
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private final MemberDaoInterface memberDao;
  private final ItemViewer itemViewer;

  public MemberViewer(MemberDaoInterface memberDao) {
    this.memberDao = memberDao;
    this.memberDao.generated(); // Generate initial data if needed
    this.itemViewer = new ItemViewer(memberDao);
  }

  /**
   * Allows a new user to sign in by providing their personal details: name,
   * email, password, and phone number. The method adds the new member to the system.
   */
  public void createMember() {
    System.out.print("Enter your name: ");
    String name = input.nextLine();
    System.out.print("Enter your email: ");
    String email = input.nextLine();
    System.out.print("Enter your password: ");
    String password = input.nextLine();
    System.out.print("Enter your phone number: ");
    String phoneNumber = input.nextLine();

    memberDao.addMember(name, email, phoneNumber, password);
    System.out.println(FeedbackMessage.SUCCESS_MEMBER_CREATION.getMessage());
    waitForUserInput();
  }

  /**
   * Allows the user to edit member information such as name, email, phone number, or password.
   */
  public void editMemberInfo() {
    System.out.print("Enter member ID: ");
    String memberId = input.nextLine();
    System.out.print("Enter new name: ");
    String name = input.nextLine();
    System.out.print("Enter new email: ");
    String email = input.nextLine();
    System.out.print("Enter new password: ");
    String password = input.nextLine();
    System.out.print("Enter new phone number: ");
    String phoneNumber = input.nextLine();

    memberDao.modifyMember(memberId, name, email, phoneNumber, password);
    System.out.println(FeedbackMessage.SUCCESS_MEMBER_UPDATE.getMessage());
    waitForUserInput();
  }

  /**
   * Allows the user to delete a member based on the provided member ID and password.
   */
  public void deleteMember() {
    System.out.print("Enter member ID: ");
    String memberId = input.nextLine();
    System.out.print("Enter password: ");
    String password = input.nextLine();

    memberDao.deleteMember(memberId, password);
    System.out.println(FeedbackMessage.SUCCESS_MEMBER_DELETION.getMessage());
    waitForUserInput();
  }

  /**
   * Displays detailed information about a specific member based on the member ID.
   */
  public void specificMemberFullInfo() {
    displayMembersOverview();
    System.out.print("Enter the member ID to view full information: ");
    String memberId = input.nextLine();
    Member member = memberDao.showSpecificMemberInfo(memberId);

    if (member != null) {
      displayMemberInfo(member);
    } else {
      System.out.println(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    waitForUserInput();
  }

  /**
   * Displays a simple list of all members, showing only basic information like name, email, and phone number.
   */
  public void displayMembersOverview() {
    List<Member> members = memberDao.getMembers();

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
  public void displayMembersWithDetailedItems() {
    List<Member> members = memberDao.getMembers();

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

  /**
   * Prompts the user to press ENTER to return to the menu.
   */
  private void waitForUserInput() {
    System.out.print("Press ENTER to go back to the menu: ");
    input.nextLine();
  }
}
