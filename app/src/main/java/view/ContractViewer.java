package view;

import controller.ContractDaoImpl;
import controller.ContractDaoInterface;
import controller.FeedbackMessage;
import controller.ItemDaoImpl;
import controller.ItemDaoInterface;
import controller.MemberDaoInterface;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.ImmutableContract;
import model.Item;
import model.Member;

/**
 * ContractViewer is responsible for displaying information about contracts
 * associated with an item. This viewer shows contract details like the
 * borrower,
 * contract start day, end day, and contract status.
 */
public class ContractViewer {
  private Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private ContractDaoInterface contractDao = new ContractDaoImpl();
  private MemberDaoInterface memberDao;
  private ItemDaoInterface itemDao = new ItemDaoImpl(memberDao);

  public ContractViewer(MemberDaoInterface memberDaoImpl) {
    memberDao = memberDaoImpl;
  }

  /**
   * Displays the contract menu with options to create a contract or view existing
   * contracts.
   */
  public void contractMenu() {
    boolean running = true;
    while (running) {
      System.out.println("\nContract Menu:");
      System.out.println("1. Create a new contract.");
      System.out.println("2. Back to main menu.");

      System.out.print("\nSelect an option: ");
      int choice = readInt();
      switch (choice) {
        case 1:
          createContract();
          break;
        case 2:
          running = false;
          break;
        default:
          System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
          break;
      }
    }
  }

  /**
   * Creates a new contract by prompting the user for relevant details.
   */
  private void createContract() {
    System.out.println("Creating a new contract...");

    // Collect necessary details for the contract
    String lenderId = promptForInput("Enter lender's member ID: ");
    String borrowerId = promptForInput("Enter borrower's member ID: ");
    String itemId = promptForInput("Enter item ID: ");
    final int startDay = promptForInt("Enter start day (integer): ");
    final int endDay = promptForInt("Enter end day (integer): ");

    // Fetch the lender, borrower, and item
    Member lender = memberDao.getMemberById(lenderId);
    Member borrower = memberDao.getMemberById(borrowerId);
    Item item = itemDao.getItemById(lender, itemId);

    // Validate fetched data
    if (lender == null) {
      System.out.println(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage() + " (Lender)");
      return;
    }
    if (borrower == null) {
      System.out.println(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage() + " (Borrower)");
      return;
    }
    if (item == null) {
      System.out.println(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
      return;
    }

    // Use the ContractDao to create the contract
    contractDao.createContract(lender, borrower, item, startDay, endDay);
    System.out.println(FeedbackMessage.SUCCESS_CONTRACT_CREATION.getMessage());
  }

  /**
   * Displays the details of active contracts associated with the given item.
   * Only contracts with "Active" status will be displayed.
   *
   * @param item The item for which contracts will be viewed.
   */
  public void viewContract(Item item) {
    List<ImmutableContract> contracts = item.getContracts();
    for (ImmutableContract contract : contracts) {
      String status = "Active";
      if (status.equals(contract.getStatus())) {
        System.out.println("   Borrower: " + contract.getBorrower().getName());
        System.out.println("   Start day of contract: " + contract.getStartDay());
        System.out.println("   End day of contract: " + contract.getEndDay());
        System.out.println("------------------------------");
      }
    }
  }

  /**
   * Reads an integer input from the user, handling invalid inputs.
   *
   * @return The integer value entered by the user.
   */
  private int readInt() {
    while (!input.hasNextInt()) {
      System.out.print("That's not a valid number. Please enter a number: ");
      input.next();
    }
    int result = input.nextInt();
    input.nextLine(); // Consume newline
    return result;
  }

  /**
   * Prompts the user for input.
   *
   * @param message The prompt message.
   * @return The user's input as a string.
   */
  private String promptForInput(String message) {
    System.out.print(message);
    return input.nextLine();
  }

  /**
   * Prompts the user for an integer input.
   *
   * @param message The prompt message.
   * @return The user's input as an integer.
   */
  private int promptForInt(String message) {
    System.out.print(message);
    while (!input.hasNextInt()) {
      System.out.print("That's not a valid number. Please enter a number: ");
      input.next();
    }
    int result = input.nextInt();
    input.nextLine(); // Consume newline
    return result;
  }
}
