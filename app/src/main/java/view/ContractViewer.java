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

public class ContractViewer {
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private final ContractDaoInterface contractDao = new ContractDaoImpl();
  private final MemberDaoInterface memberDao;
  private final ItemDaoInterface itemDao;

  public ContractViewer(MemberDaoInterface memberDao) {
    if (memberDao == null) {
      throw new IllegalArgumentException("MemberDaoInterface cannot be null.");
    }
    this.memberDao = memberDao;
    this.itemDao = new ItemDaoImpl(this.memberDao);
  }

  public void createContract() {
    System.out.println("Creating a new contract...");
    String lenderId = promptForInput("Enter lender's member ID: ");
    String borrowerId = promptForInput("Enter borrower's member ID: ");
    String itemId = promptForInput("Enter item ID: ");
    int startDay = promptForInt("Enter start day (integer): ");
    int endDay = promptForInt("Enter end day (integer): ");

    Member lender = memberDao.getMemberById(lenderId);
    Member borrower = memberDao.getMemberById(borrowerId);
    Item item = itemDao.getItemById(lender, itemId);

    contractDao.createContract(lender, borrower, item, startDay, endDay);
    System.out.println(FeedbackMessage.SUCCESS_CONTRACT_CREATION.getMessage());
  }

  public void viewContract(Item item) {
    List<ImmutableContract> contracts = item.getContracts();
    for (ImmutableContract contract : contracts) {
      if ("Active".equals(contract.getStatus())) {
        System.out.println("   Borrower: " + contract.getBorrower().getName());
        System.out.println("   Start day of contract: " + contract.getStartDay());
        System.out.println("   End day of contract: " + contract.getEndDay());
        System.out.println("------------------------------");
      }
    }
  }

  private String promptForInput(String message) {
    System.out.print(message);
    return input.nextLine();
  }

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
