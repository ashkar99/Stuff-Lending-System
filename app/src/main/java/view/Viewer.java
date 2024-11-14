package view;


/**
 * Centralized Viewer class for displaying various system menuss.
 */
public class Viewer extends BaseViewer{

  /**
   * Main menu, providing access to other menus in the system.
   */
  public int mainMenu() {
    System.out.println("\nWelcome to Stuff Lending System App!");
    System.out.println("1. Member Menu.");
    System.out.println("2. Contract Menu.");
    System.out.println("3. Item Menu.");
    System.out.println("4. Exit.");
    System.out.print("Select an option: ");
    int choice = promptForInt("");
    return choice;
  }

  /**
   * Displays the Member Menu.
   */
  public int memberMenu() {
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
    return choice;
  }

  /**
   * Displays the Contract Menu.
   */
  public void contractMenu() {

    System.out.println("\nContract Menu:");
    System.out.println("1. Create a new contract.");
    System.out.println("2. Back to main menu.");
    System.out.print("\nSelect an option: ");

  }

  /**
   * Displays the Item Menu.
   */
  public void itemMenu() {
    System.out.println("\nItem Menu:");
    System.out.println("1. Add a new item.");
    System.out.println("2. Edit item info.");
    System.out.println("3. Delete an item.");
    System.out.println("4. View available items.");
    System.out.println("5. Back to main menu.");
    System.out.print("Select an option: ");
  }
}
