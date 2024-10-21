package view;

import java.util.List;
import model.ImmutableContract;
import model.Item;
/**
 * Contract viewer.
 */
public class ContractViewer {


  public void viewContract(Item item) {
    List<ImmutableContract> contracts = item.getContracts();
    for (ImmutableContract contract : contracts) {
      String status = "Active";
      if (status.equals((contract.getStatus()))) {
        System.out.println("   Borrower: " + contract.getBorrower().getName());
        System.out.println("   Start day of contract: " + contract.getStartDay());
        System.out.println("   End day of contract: " + contract.getEndDay());
        System.out.println("------------------------------");

      }

    }
  }

}
