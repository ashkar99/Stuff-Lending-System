package view;

import controller.MemberDaoImpl;
import controller.MemberDaoInterface;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Item;
import model.Member;

/**
 * The MemberViewer class handles the user interface for interacting with
 * members.
 * It allows users to log in, sign in, delete members, and view member
 * information in different formats.
 */
public class MemberViewer {
  private Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private MemberDaoInterface memberDaoImpl = new MemberDaoImpl();

  /**
   * Default constructor for the MemberViewer class.
   */
  public MemberViewer() {

  }

  /**
   * Handles the login process, where the user provides email and password.
   * The method displays a menu with options to list all members, delete a member,
   * and view detailed information about specific members.
   */
  public void menu() {

    String choice = "";
    while (choice != "9") {

      System.out.println("Welcome to Stuff Lending System App");
      System.out.println("Press S to list all member");
      System.out.println("Press d to delete a member");
      System.out.println("Press d to delete a member");
      System.out.println("enter 9 to quit");
      choice = input.nextLine().toLowerCase();

      switch (choice) {
        case "s":
          showSimpleAllMembers();
          break;
        case "d":
          deleteMember();
          break;
        case "l":
          specificFullInfo();
          break;
        case "v":
          showVerboseAllMembers();
          break;
        case "e":
          editMemberInfo();
          break;
        default:
          break;
      }
    }
  }

  private void editMemberInfo() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'editMemberInfo'");
  }

  /**
   * Displays detailed information about a specific member based on the member ID.
   * It first shows a list of all members, and then the user can select a member
   * to view.
   */
  private void specificFullInfo() {
    showSimpleAllMembers();
    System.out.println("Enter the memberId of the selected member");
    String memberId = input.nextLine();
    Member member = memberDaoImpl.showSpecificMemberInfo(memberId);
    System.out.println("----------------------------------------");
    System.out.println("Member ID: " + member.getId());
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.err.println("Phone number: " + member.getPhoneNumber());
    System.err.println("Current credits: " + member.getCredits());
    System.out.println("Number of owned items: " + member.getItems().size());
    System.out.println("----------------------------------------");

  }

  /**
   * Allows the user to delete a member based on the provided email and password.
   */
  private void deleteMember() {
    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    memberDaoImpl.deleteMember(email, password);
  }

  /**
   * Allows a new user to sign in by providing their personal details: name,
   * email, password, and phone number.
   * The method adds the new member to the system.
   */
  public void createMember() {
    System.out.println("ENTER YOUR NAME:");
    final String name = input.nextLine();
    System.out.println("ENTER YOUR EMAIL:");
    final String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    final String password = input.nextLine();
    System.out.println("ENTER YOUR PHONE NUMBER:");
    final String phonNum = input.nextLine();
    memberDaoImpl.addMember(name, email, phonNum, password);
  }

  /**
   * Displays a simple list of all members, showing only basic information like
   * name, email, and phone number.
   */
  private void showSimpleAllMembers() {
    List<Member> simplList = memberDaoImpl.getMembers();
    for (Member member : simplList) {
      System.out.println("----------------------------------------");
      System.out.println("Member ID: " + member.getId());
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      System.err.println("Current credits: " + member.getCredits());
      System.out.println("Number of owned items: " + member.getItems().size());
      System.out.println("----------------------------------------");
    }
  }

  /**
   * Displays a verbose list of all members, showing detailed information about
   * each member.
   */
  private void showVerboseAllMembers() {
    List<Member> verboseList = memberDaoImpl.getMembers();
    for (Member member : verboseList) {
      System.out.println("----------------------------------------");
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      List<Item> items = member.getItems();
      for (Item item : items) {
        System.out.println("Item name: " + item.getName());
        System.out.println("Item Description: " + item.getDescription());
        System.out.println("Category: " + item.getCategory());
        System.out.println("Item cost per day: " + item.getCostPerDay());

      }
      System.out.println("");
      System.out.println("----------------------------------------");
    }
  }
}
