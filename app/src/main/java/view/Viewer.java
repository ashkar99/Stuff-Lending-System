package view;

import controller.FeedbackMessage;
import controller.MemberDaoImpl;
import controller.MemberDaoInterface;

/**
 * Centralized Viewer class for displaying various system menuss.
 */
public class Viewer extends BaseViewer {
  private final ContractViewer contractViewer = new ContractViewer();
  private final ItemViewer itemViewer = new ItemViewer();

  /**
   * Main menu, providing access to other menus in the system.
   */
  public void mainMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nWelcome to Stuff Lending System App!");
      System.out.println("1. Member Menu.");
      System.out.println("2. Contract Menu.");
      System.out.println("3. Item Menu.");
      System.out.println("4. Exit.");
      System.out.print("Select an option: ");
      
      int choice = promptForInt("");
      switch (choice) {
        case 1 -> memberMenu();
        case 2 -> contractMenu();
        case 3 -> itemMenu();
        case 4 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
      }
    }
  }

  /**
   * Displays the Member Menu.
   */
  private void memberMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nMember Menu:");
      System.out.println("1. Create a member.");
      System.out.println("2. Edit member info.");
      System.out.println("3. Delete a member.");
      System.out.println("4. Show specific member full information.");
      System.out.println("5. Display members overview.");
      System.out.println("6. Display members and their items.");
      System.out.println("7. Back to main menu.");
      System.out.print("Select an option: ");
      
      int choice = promptForInt("");
      switch (choice) {
        case 1 -> memberViewer.createMember();
        case 2 -> memberViewer.editMemberInfo();
        case 3 -> memberViewer.deleteMember();
        case 4 -> memberViewer.specificMemberFullInfo();
        case 5 -> memberViewer.displayMembersOverview();
        case 6 -> memberViewer.displayMembersWithDetailedItems();
        case 7 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
      }
    }
  }

  /**
   * Displays the Contract Menu.
   */
  private void contractMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nContract Menu:");
      System.out.println("1. Create a new contract.");
      System.out.println("2. Back to main menu.");

      System.out.print("\nSelect an option: ");
      int choice = promptForInt("");
      switch (choice) {
        case 1 -> contractViewer.createContract();
        case 2 -> running = false;
        default -> System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
      }
    }
  }

  /**
   * Displays the Item Menu.
   */
  private void itemMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nItem Menu:");
      System.out.println("1. Add a new item.");
      System.out.println("2. Edit item info.");
      System.out.println("3. Delete an item.");
      System.out.println("4. View available items.");
      System.out.println("5. Back to main menu.");
      System.out.print("Select an option: ");
      
      int choice = promptForInt("");
      switch (choice) {
        case 1 -> itemViewer.addNewItem();
        case 2 -> itemViewer.editItemInfo();
        case 3 -> itemViewer.deleteItem();
        case 4 -> itemViewer.viewAvailableItems();
        case 5 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
      }
    }
  }
}
