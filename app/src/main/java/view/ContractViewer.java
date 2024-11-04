package view;

import controller.ContractDaoImpl;
import controller.ContractDaoInterface;
import controller.FeedbackMessage;
import controller.ItemDaoImpl;
import controller.ItemDaoInterface;
import controller.MemberDaoInterface;
import java.util.List;
import model.ImmutableContract;
import model.Item;
import model.Member;

/**
 * ContractViewer is responsible for managing contract-related interactions
 * in the user interface, including creating contracts and viewing active
 * contracts associated with an item.
 */
public class ContractViewer extends BaseViewer {
  private final ContractDaoInterface contractDao = new ContractDaoImpl();
  private final MemberDaoInterface memberDao;
  private final ItemDaoInterface itemDao;

  /**
   * Initializes a new ContractViewer with a reference to MemberDaoInterface
   * and creates an instance of ItemDaoImpl based on the given MemberDao.
   *
   * @param memberDao An instance of MemberDaoInterface to retrieve member
   *                  information.
   * @throws IllegalArgumentException if memberDao is null.
   */
  public ContractViewer(MemberDaoInterface memberDao) {
    if (memberDao == null) {
      throw new IllegalArgumentException("MemberDaoInterface cannot be null.");
    }
    this.memberDao = memberDao;
    this.itemDao = new ItemDaoImpl(this.memberDao);
  }

  /**
   * Creates a new contract by prompting the user for the lender's and
   * borrower's member IDs, item ID, and the start and end days for the contract.
   * Once validated, it initiates the contract creation process.
   */
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
    waitForUserInput();
  }

  /**
   * Displays all active contracts associated with the specified item.
   * Each active contract includes details such as the borrower,
   * start day, and end day of the contract.
   *
   * @param item The item for which active contracts will be viewed.
   */
  public void viewContract(Item item) {
    List<ImmutableContract> contracts = item.getContracts();
    for (ImmutableContract contract : contracts) {
      System.out.println("   Borrower: " + contract.getBorrower().getName());
      System.out.println("   Start day of contract: " + contract.getStartDay());
      System.out.println("   End day of contract: " + contract.getEndDay());
      System.out.println("------------------------------");
    }
  }
}
