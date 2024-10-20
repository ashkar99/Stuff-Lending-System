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
      String status = "activ";
      if (status.equals((contract.getStatus()))) {
        //memberViewer.viewBorrower(contract.getBorrower());
        System.out.println("Start day of contract: " + contract.getStartDay());
        System.out.println("End day of contract: " + contract.getEndDay());

      }

    }
  }

}
