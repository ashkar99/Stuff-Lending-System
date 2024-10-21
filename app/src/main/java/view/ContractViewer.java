package view;

import java.util.List;
import model.ImmutableContract;
import model.Item;

/**
 * ContractViewer is responsible for displaying information about contracts
 * associated with an item. This viewer shows contract details like the
 * borrower,
 * contract start day, end day, and contract status.
 */
public class ContractViewer {

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
}
