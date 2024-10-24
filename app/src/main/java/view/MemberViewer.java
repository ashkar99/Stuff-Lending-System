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
 * members. It allows users to log in, sign in, delete members, and view member
 * information in different formats.
 */
public class MemberViewer {
  private Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private MemberDaoInterface memberDaoImpl = new MemberDaoImpl();
  private ItemViewer itemViewer;

  /**
   * Default constructor for the MemberViewer class.
   */
  public MemberViewer() {
    memberDaoImpl.generated();
    itemViewer = new ItemViewer(memberDaoImpl);
  }

  /**
   * Handles the login process, where the user provides email and password.
   * The method displays a menu with options to list all members, delete a member,
   * and view detailed information about specific members.
   */
  public void mainMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nWelcome to Stuff Lending System App!");
      System.out.println("1. Create a member.");
      System.out.println("2. Edit menu to member info.");
      System.out.println("3. Show specific member full information.");
      System.out.println("4. Display members overview.");
      System.out.println("5. Display members information and their items.");
      System.out.println("6. View avilible items.");
      System.out.println("7. Exit.");

      System.out.print("\nSelect an option: ");
      int choice = readInt();
      switch (choice) {
        case 1:
          createMember();
          break;
        case 2:
          editMemberInfoMenu();
          break;
        case 3:
          specificMemberFullInfo();
          break;
        case 4:
          displayMembersOverview();
          break;
        case 5:
          displayMembersWithDetailedItems();
          break;
        case 6:
          getAvilbaleItems();
          break;
        case 7:
          running = false;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
          break;
      }
    }
  }

  private void getAvilbaleItems() {
    List<Item> avItems = memberDaoImpl.getAvilbaleItems();
    itemViewer.viewAvailableItems(avItems);

  }

  private void editMemberInfoMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nWelcome to Stuff Lending System App!");
      System.out.println("1. Edit member info.");
      System.out.println("2. Delete a member.");
      System.out.println("3. Edit item info.");
      System.out.println("4. Add a new item.");
      System.out.println("5. Delete a item");
      System.out.println("7. Exit.");

      System.out.print("\nSelect an option: "); // Changed to print as this is where the user will type
      int choice = readInt();
      switch (choice) {
        case 1:
          editMemberInfo();
          break;
        case 2:
          deleteMember();
          break;
        case 3:
          itemViewer.editIteminfo();
          break;
        case 4:
          itemViewer.addNewItem();
          break;
        case 5:
          itemViewer.deleteItem();
          break;
        case 6:
          displayMembersWithDetailedItems();
          break;
        case 7:
          running = false;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
          break;
      }
    }
  }

  /**
   * Allows a new user to sign in by providing their personal details: name,
   * email, password, and phone number. The method adds the new member to the
   * system.
   */
  private void createMember() {
    System.out.print("ENTER YOUR NAME: ");
    final String name = input.nextLine();
    System.out.print("ENTER YOUR EMAIL: ");
    final String email = input.nextLine();
    System.out.print("ENTER YOUR PASSWORD: ");
    final String password = input.nextLine();
    System.out.print("ENTER YOUR PHONE NUMBER: ");
    final String phonNum = input.nextLine();
    memberDaoImpl.addMember(name, email, phonNum, password);
    waitForUserInput();
  }

  /**
   * Edits member information such as name, email, phone number, or password.
   */
  private void editMemberInfo() {
    System.out.print("ENTER MEMBER ID: ");
    final String memberId = input.nextLine();
    System.out.print("ENTER YOUR NAME: ");
    final String name = input.nextLine();
    System.out.print("ENTER YOUR EMAIL: ");
    final String email = input.nextLine();
    System.out.print("ENTER YOUR PASSWORD: ");
    final String password = input.nextLine();
    System.out.print("ENTER YOUR PHONE NUMBER: ");
    final String phonNum = input.nextLine();
    memberDaoImpl.modifyMember(memberId, name, email, phonNum, password);
    waitForUserInput();
  }

  /**
   * Allows the user to delete a member based on the provided email and password.
   */
  private void deleteMember() {
    System.out.print("ENTER YOUR MEMBER ID: ");
    String memberId = input.nextLine();
    System.out.print("ENTER YOUR PASSWORD: ");
    String password = input.nextLine();
    memberDaoImpl.deleteMember(memberId, password);
    waitForUserInput();
  }

  /**
   * Displays detailed information about a specific member based on the member ID.
   * It first shows a list of all members, and then the user can select a member
   * to view.
   */
  private void specificMemberFullInfo() {
    displayMembersOverview();
    System.out.print("Enter the memberId of the selected member to display its information: ");
    String memberId = input.nextLine();
    Member member = memberDaoImpl.showSpecificMemberInfo(memberId);
    System.out.println("----------------------------------------");
    System.out.println("Member ID: " + member.getId());
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getPhoneNumber());
    System.out.println("Current credits: " + member.getCredits());
    System.out.println("Number of owned items: " + member.getItems().size());
    System.out.println("----------------------------------------");
    waitForUserInput();

  }

  /**
   * Displays a simple list of all members, showing only basic information like
   * name, email, and phone number.
   */
  private void displayMembersOverview() {
    List<Member> simplList = memberDaoImpl.getMembers();
    for (Member member : simplList) {
      System.out.println("----------------------------------------");
      System.out.println("Member ID: " + member.getId());
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      System.out.println("Current credits: " + member.getCredits());
      System.out.println("Number of owned items: " + member.getItems().size());
      System.out.println("----------------------------------------");
    }
  }

  /**
   * Displays a verbose list of all members, showing detailed information about
   * each member.
   */
  private void displayMembersWithDetailedItems() {
    List<Member> detailedList = memberDaoImpl.getMembers();
    for (Member member : detailedList) {
      System.out.println("----------------------------------------");
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      itemViewer.viewItems(member);
      System.out.println("----------------------------------------");
    }
  }

  /**
   * Displays the information about the borrower member.
   *
   * @param borrower The member borrowing an item.
   */
  public void viewBorrower(Member borrower) {
    System.out.println("Name of borrower: " + borrower.getName());
  }

  /**
   * Safely reads an integer from the user, prompting again if the input is not a
   * valid integer.
   *
   * @return The integer input by the user.
   */
  private int readInt() {
    while (!input.hasNextInt()) {
      System.out.print("That's not a valid number. Please enter a number: ");
      input.next();
    }
    int result = input.nextInt();
    input.nextLine();
    return result;
  }

  /**
   * Leave the user read in piece.
   */
  private void waitForUserInput() {
    System.out.print("Press ENTER to go back to the menu: ");
    input.nextLine();
  }

}
