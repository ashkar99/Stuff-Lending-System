package view;


import java.util.List;
import model.ImmutableContract;
import model.Item;


/**
 * ContractViewer is responsible for managing contract-related interactions
 * in the user interface, including creating contracts and viewing active
 * contracts associated with an item.
 */
public class ContractViewer extends BaseViewer {

  /**
   * Initializes a new ContractViewer with a reference to MemberDaoInterface
   * and creates an instance of ItemDaoImpl based on the given MemberDao.
   *
   * @param memberDao An instance of MemberDaoInterface to retrieve member
   *                  information.
   * @throws IllegalArgumentException if memberDao is null.
   */
  public ContractViewer() {
   
  }

  /**
   * Creates a new contract by prompting the user for the lender's and
   * borrower's member IDs, item ID, and the start and end days for the contract.
   * Once validated, it initiates the contract creation process.
   */
  public String [] createContract() {
    System.out.println("Creating a new contract...");
    String lenderId = promptForInput("Enter lender's member ID: ");
    String borrowerId = promptForInput("Enter borrower's member ID: ");
    String itemId = promptForInput("Enter item ID: ");
    String startDay = promptForInput("Enter start day (integer): ");
    String endDay = promptForInput("Enter end day (integer): ");
    String [] contract = {lenderId, borrowerId,itemId,startDay,endDay};
    return contract;
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
